package com.showmual.controller;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.showmual.model.BoardVo;
import com.showmual.model.LikeVo;
import com.showmual.model.ReplyVo;
import com.showmual.model.TotalVo;
import com.showmual.service.CommunityService;
import com.showmual.service.ReplyService;
import com.showmual.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/closet")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @Autowired
    UserService userService;
    
    @Autowired
    ReplyService replyService;

    @Autowired
    BoardVo boardVo;

    @Autowired
    TotalVo totalVo;
    
    @Autowired
    LikeVo likeVo;
    
    List<TotalVo> joinBoardList = new ArrayList<TotalVo>();
    List<ReplyVo> replyList = new ArrayList<ReplyVo>();
    
    // 커뮤니티 페이지
    @RequestMapping(value = "/community", method = RequestMethod.GET)
    public String getCommunityList(Model model) {

        joinBoardList = communityService.listArticles();

//        for (TotalVo b : joinBoardList) {
//            System.out.println(b);
//        }

        model.addAttribute("communityList", joinBoardList);
        
        return "community";
    }

    // 커뮤니티 새글쓰기
    @RequestMapping("/communityUpload")
    public String writeCommunity() {
        return "communityUpload";
    }

    // 커뮤니티 글 추가
    @RequestMapping(value = "/addCommunity", method = RequestMethod.POST)
    public String addCommunity(HttpServletRequest request, Principal principal,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "contents") String contents,
            @RequestPart MultipartFile files) throws Exception {

        // 로그인한 사용자의 아이디를 가져온다.
        String username = principal.getName();
        String id = userService.findIdByUsername(username);
        Long longId = Long.parseLong(id);

        if (!files.isEmpty()) { // 업로드할 파일이 존재할 경우에만
            String sourceFileName = files.getOriginalFilename();
            String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
            File destinationFile;
            String destinationFileName;
            // 경로
            String fileUrl = "E:/Project/images/community/"; // 커뮤니티 폴더 생성

            do {
                destinationFileName = RandomStringUtils.randomAlphanumeric(16) + "_" + id + "."
                        + sourceFileNameExtension;
                destinationFile = new File(fileUrl + destinationFileName);
            } while (destinationFile.exists());

            destinationFile.getParentFile().mkdirs();
            files.transferTo(destinationFile);

            // DB에 데이터 넣기
            boardVo.setUserId(longId);
            boardVo.setTitle(title);
            boardVo.setContents(contents);
            boardVo.setImagePath(fileUrl);
            boardVo.setImageName(destinationFileName);

            communityService.addArticle(boardVo);

        }

        return "redirect:/closet/community"; // client ---> server addArticle로 들어온거.
        // server----> client 요청한거 다 끝냈으니깐 listArticles로 더 불러줄래? ---> listArticles로 다시
        // 불러줌.
    }

    // 커뮤니티 상세보기
    @RequestMapping(value = "/viewCommunity", method = RequestMethod.GET)
    public ModelAndView viewCommunity(@RequestParam(value = "boardNo") int boardNo, Principal principal) {
        
        // 로그인한 사용자의 아이디를 가져온다.
        String username = principal.getName();
        String id = userService.findIdByUsername(username);
        Long longId = Long.parseLong(id);
        
        totalVo = communityService.viewArticle(boardNo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("viewCommunity");
        mv.addObject("viewCommunity", totalVo);
        
        // 댓글 조회
        replyList = replyService.replyList(boardNo);
        mv.addObject("replyList", replyList);
        
        // 좋아요 눌렀는지 안눌렀는지
        mv.addObject("like", communityService.findLike(boardNo, longId));
        
        // 좋아요 개수
        mv.addObject("countLike", communityService.countLike(boardNo));
        
        System.out.println(mv);
        
        return mv;
    }
    
    // 커뮤니티 글 수정페이지
    @RequestMapping(value = "/editCommunity", method = RequestMethod.GET)
    public ModelAndView editCommunity(@RequestParam(value = "boardNo") int boardNo) {
        totalVo = communityService.viewArticle(boardNo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editCommunity");
        mv.addObject("editCommunity", totalVo);
        
        //System.out.println(mv);
        
        return mv;
    }
    
    
    
    // 커뮤니티 글 수정
    @RequestMapping(value = "/doEditCommunity", method = RequestMethod.POST)
    public String editArticle(@RequestParam(value = "boardNo") String boardNo,
            @RequestParam(value = "title") String title, @RequestParam(value = "contents") String content,
            // redirect 할 때 파라미터 값을 가지고 가야할 경우에는 RedirectAttributes를 사용한다.
            RedirectAttributes redirect) throws Exception {
        
        boardVo.setBoardNo(Integer.parseInt(boardNo));
        boardVo.setTitle(title);
        boardVo.setContents(content);
        
        communityService.editArticle(boardVo);
        
        // viewArticle은 파라미터 값이 필요하므로 위에서 만들어 둔 redirect를 사용한다.
        redirect.addAttribute("boardNo", boardNo);
        return "redirect:viewCommunity";
    }
    
    // 커뮤니티 글 삭제
    @RequestMapping(value = "/removeCommunity", method = RequestMethod.GET)
    public String removeArticle(@RequestParam final int boardNo) {
        communityService.removeArticle(boardNo);
        return "redirect:community";
    }
    
    
    // 좋아요
    @RequestMapping(value = "/likeUp", method = RequestMethod.GET)
    public String likeup(HttpServletRequest request, Principal principal, RedirectAttributes redirect,
            @RequestParam(value = "boardNo") int boardNo) {
        
        // 로그인한 사용자의 번호(int)를 가져온다.
        String username = principal.getName();
        String id = userService.findIdByUsername(username);
        Long longId = Long.parseLong(id);
        
        // DB에 데이터 넣기
        likeVo.setBoardNo(boardNo);
        likeVo.setUserId(longId);
        
        communityService.likeUp(likeVo);
        
        redirect.addAttribute("boardNo", boardNo);
        return "redirect:/closet/viewCommunity";
    }
    
    // 좋아요 취소
    @RequestMapping(value = "/likeDown", method = RequestMethod.GET)
    public String likeDown(RedirectAttributes redirect, Principal principal,
            @RequestParam(value = "boardNo") int boardNo) {
        
        // 로그인한 사용자의 번호(int)를 가져온다.
        String username = principal.getName();
        String id = userService.findIdByUsername(username);
        Long longId = Long.parseLong(id);
        
        communityService.likeDown(boardNo, longId);
        
        redirect.addAttribute("boardNo", boardNo);
        return "redirect:/closet/viewCommunity";
    }
    
}
