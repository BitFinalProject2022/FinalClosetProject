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

import com.showmual.model.DiaryVo;
import com.showmual.service.DiaryService;
import com.showmual.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/closet")
public class DiaryController {
    
	@Autowired
	DiaryService diaryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DiaryVo diaryVo;
	
	List<DiaryVo> diaryList = new ArrayList<DiaryVo>();
	
	
	// 다이어리 페이지
	@RequestMapping(value = "/diary", method = RequestMethod.GET)
	public String getDiaryList(Model model, Principal principal) {
	    
	    // 로그인한 사용자의 username을 가져와서 id(int)를 얻는다.
        String username = principal.getName();
        String id = userService.findIdByUsername(username);
        Long longId = Long.parseLong(id);  // id를 Integer형으로 바꾼다.
	    
	    diaryList = diaryService.listDiarys(longId);
	    
//	    for(DiaryVo k : diaryList) {
//            System.out.println(k);
//        }
	    
		model.addAttribute("diaryList", diaryList);
		return "diary";
	}
	
	// 다이어리 글쓰기
	@RequestMapping("/diaryUpload")
	public String writeDiary() {
		return "diaryUpload";
	}
	
	// 다이어리 글 추가 기능
	@RequestMapping(value = "/addDiary", method = RequestMethod.POST)
	public String addDiary(Model model, HttpServletRequest request,
	        @RequestParam(value="title") String title,
			@RequestParam(value = "contents") String contents, 
			// @RequestParam(value = "diary_image") String diary_image,
			@RequestPart MultipartFile files, Principal principal) throws Exception {
		
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
			String fileUrl = "E:/Project/images/diary/" + id + "/"; // 아이디 번호로 폴더 생성

			do {
				// 이미지 이름 뒤에 png 붙인다. (png 형태로 저장)
				destinationFileName = RandomStringUtils.randomAlphanumeric(16) + "_" + id + "."
						+ sourceFileNameExtension;
				destinationFile = new File(fileUrl + destinationFileName);
			} while (destinationFile.exists());

//			String imgUrl = fileUrl + destinationFileName;

			destinationFile.getParentFile().mkdirs();
			files.transferTo(destinationFile);

			// DB에 데이터 넣기
			diaryVo.setTitle(title);
			diaryVo.setContents(contents);
			diaryVo.setUserId(longId);
			diaryVo.setImagePath(fileUrl);
			diaryVo.setImageName(destinationFileName);

	        diaryService.addDiary(diaryVo);
			
			
		}

		return "redirect:/closet/diary"; // client ---> server addDiary로 들어온거.
	}

	// 다이어리 상세보기
	@RequestMapping(value = "/viewDiary", method = RequestMethod.GET)
	public ModelAndView viewDiary(@RequestParam(value = "diaryNo") int diaryNo) {
		diaryVo = diaryService.viewDiary(diaryNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewDiary");
		mv.addObject("viewDiary", diaryVo);
		
		return mv;
	}
	
	// 다이어리 글 수정페이지
    @RequestMapping(value = "/editDiary", method = RequestMethod.GET)
    public ModelAndView editCommunity(@RequestParam(value = "diaryNo") int diaryNo) {
        diaryVo = diaryService.viewDiary(diaryNo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editDiary");
        mv.addObject("editDiary", diaryVo);
        
        //System.out.println(mv);
        
        return mv;
    }
    
    // 다이어리 글 수정
    @RequestMapping(value = "/doEditDiary", method = RequestMethod.POST)
    public String editArticle(@RequestParam(value = "diaryNo") String diaryNo,
            @RequestParam(value = "title") String title, @RequestParam(value = "contents") String content,
            // redirect 할 때 파라미터 값을 가지고 가야할 경우에는 RedirectAttributes를 사용한다.
            RedirectAttributes redirect) throws Exception {
        
        diaryVo.setDiaryNo(Integer.parseInt(diaryNo));
        diaryVo.setTitle(title);
        diaryVo.setContents(content);
        
        diaryService.editDiary(diaryVo);
        
        // viewArticle은 파라미터 값이 필요하므로 위에서 만들어 둔 redirect를 사용한다.
        redirect.addAttribute("diaryNo", diaryNo);
        return "redirect:viewDiary";
    }

    // 다이어리 글 삭제
    @RequestMapping(value = "/removeDiary", method = RequestMethod.GET)
    public String removeArticle(@RequestParam final int diaryNo) {
        diaryService.removeDiary(diaryNo);
        return "redirect:diary";
    }
	
}
