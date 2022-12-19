package com.showmual.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.showmual.model.QnaVo;
import com.showmual.service.QnaService;
import com.showmual.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/closet")
public class QnaController {

    @Autowired
    QnaService qnaService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    QnaVo qnaVo;
    
    List<QnaVo> qnaList = new ArrayList<QnaVo>();
    
    
    // 문의사항 보기 (관리자 페이지)
    @RequestMapping(value = "/admin/qna", method = RequestMethod.GET)
    public String qnaPage(Model model) {
          
        qnaList = qnaService.listQnA();

        for (QnaVo b : qnaList) {
            System.out.println(b);
        }
        
        model.addAttribute("qnaList", qnaList);
        
        return "qna";
    }
    
    
    // 문의사항 등록하기
    @RequestMapping(value = "/addQnA", method = RequestMethod.POST)
    public String addQnA(HttpServletRequest request, Principal principal,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content) throws Exception {
        
        // 로그인한 사용자의 아이디를 가져온다.
        String username = principal.getName();
        String email = userService.findEmailByUsername(username);
        
        // DB에 데이터 넣기
        qnaVo.setTitle(title);
        qnaVo.setContent(content);
        qnaVo.setEmail(email);
        qnaVo.setUsername(username);
        
        qnaService.addQnA(qnaVo);
        
        return "redirect:/closet/contact";
    }
    
    
    // 문의사항 상세보기
    @RequestMapping(value = "/admin/viewQna", method = RequestMethod.GET)
    public ModelAndView viewQnA(@RequestParam(value = "qnaNo") int qnaNo) {
        
        qnaVo = qnaService.viewQnA(qnaNo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("viewQna");
        mv.addObject("viewQna", qnaVo);
        
        System.out.println(mv);
        
        return mv;
    }
    
    
    // 문의사항 답변 보내기
    @RequestMapping(value = "/admin/answerQna", method = RequestMethod.POST)
    public String find_pw(@RequestParam(value = "content") String content,
                          @RequestParam(value = "username") String username,
                          @RequestParam(value = "title") String title,
                          @RequestParam(value = "qnaNo") int qnaNo,
                          @RequestParam(value = "email") String email) throws Exception {
        
        String nickname = userService.findNicknameByUsername(username);
        
        // 메일 보내기
        qnaService.answerQnA(content, email, nickname);
        
        
        String completedTitle = title + "        <답변 완료>"; // 문의 제목에 <답변완료> 붙이기
        
        qnaVo.setQnaNo(qnaNo);
        qnaVo.setTitle(completedTitle);
        
        // 문의 제목에 <답변 완료> 붙이기
        qnaService.editQnA(qnaVo);
        
        return "redirect:/closet/admin/qna";
    }
    
}
