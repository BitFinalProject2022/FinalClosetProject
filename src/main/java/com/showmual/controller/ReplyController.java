package com.showmual.controller;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.showmual.model.ReplyVo;
import com.showmual.service.ReplyService;
import com.showmual.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/closet")
public class ReplyController {
    
    @Autowired
    ReplyService replyService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ReplyVo replyVo;
    
    //------------------------------------------------------------
    // 댓글 불러오는 기능은 CommunityController의 viewCommunity에서 구현
    //------------------------------------------------------------
    
    // 댓글 작성
    @RequestMapping(value = "/writeReply", method = RequestMethod.POST)
    public String writeReply(HttpServletRequest request, Principal principal, RedirectAttributes redirect,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "bno") int bno) throws Exception {
        
        // 로그인한 사용자의 번호(int)를 가져온다.
        String username = principal.getName();
        String id = userService.findIdByUsername(username);
        Long longId = Long.parseLong(id);
        
        // DB에 데이터 넣기
        replyVo.setBno(bno);
        replyVo.setUserId(longId);
        replyVo.setContent(content);

        replyService.replyWrite(replyVo);
        
        // viewArticle은 파라미터 값이 필요하므로 위에서 만들어 둔 redirect를 사용한다.
        redirect.addAttribute("boardNo", bno);
        return "redirect:/closet/viewCommunity";
    }
    
    //-----------------------------------------------------------------------
    // 수정은 기회가 되면...
    //-----------------------------------------------------------------------
    
    
    // 댓글 삭제
    @RequestMapping(value = "/removeReply", method = RequestMethod.GET)
    public String removeArticle(RedirectAttributes redirect,
                                @RequestParam(value = "rno") int rno, 
                                @RequestParam(value = "bno") int bno) {
        
        replyService.replyDelete(rno);
        
        // viewArticle은 파라미터 값이 필요하므로 위에서 만들어 둔 redirect를 사용한다.
        redirect.addAttribute("boardNo", bno);
        return "redirect:/closet/viewCommunity";
    
    }
    
    
}
