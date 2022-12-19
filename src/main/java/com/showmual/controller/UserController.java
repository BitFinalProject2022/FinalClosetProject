package com.showmual.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.showmual.model.UserDto;
import com.showmual.service.UserService;
import com.showmual.validate.CheckUserIdValidator;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/closet")
public class UserController {
	
	private final CheckUserIdValidator checkUserIdValidator;
	
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;

	
	/* 커스텀 유효성 검증을 위해 추가 */
	@InitBinder
	public void validatorBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(checkUserIdValidator);
	}
	
	// 메인 페이지
	@RequestMapping(value = {"/","/index",""})
	public String index() {
		return "index";
	}

	// 회원가입 처리
	@PostMapping("/signup")
	public String execSignup(@Valid UserDto userDto, Errors errors, Model model) {
		if (errors.hasErrors()) {

			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = userService.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}

			return "login";
		}

		userService.joinUser(userDto);
		return "redirect:/closet/login";
	}
	
	// 아이디 중복체크
    @RequestMapping(value = "/idCheck", method = RequestMethod.POST)
    @ResponseBody
    public Long idCheck(@RequestParam("id") String username) {
        
        Long cnt = userService.countByUsername(username);
        return cnt;
    }
	
    // 닉네임 중복체크
    @RequestMapping(value = "/nicknameCheck", method = RequestMethod.POST)
    @ResponseBody
    public Long nicknameCheck(@RequestParam("nickname") String nickname) {
        
        Long cnt = userService.countByNickname(nickname);
        return cnt;
    }
    
	// 로그인 페이지
	@GetMapping("/login")
	public String login() {
	    
		return "login";
	}

	// 접근 거부 페이지
	@GetMapping("/denied")
	public String dispDenied() {
	    
		return "denied";
	}

	// 내 정보 페이지
	@GetMapping("/myinfo")
	public String dispMyInfo() {
	    
		return "myinfo";
	}

	// Contact 페이지
	@RequestMapping("/contact")
	public String contact() {
		
		return "contact";
	}
	
	// 나만의 옷장 페이지
    @GetMapping("/closet")
    public String closet() {
        
        return "closet";
    }

    // 코디 추천 페이지
    @GetMapping("/coordi")
    public String coordinate() {
        
        return "coordi";
    }
    
    // 마이페이지
    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String getCommunityList(Model model, Principal principal) {
        
        // 로그인한 사용자의 아이디를 가져온다.
        String username = principal.getName(); // 아이디
        String id = userService.findIdByUsername(username); // 회원번호
        Long longId = Long.parseLong(id);
        String nickname = userService.findNicknameByUsername(username); // 닉네임
        String email = userService.findEmailByUsername(username); // 이메일
        String password = userService.findPasswordByUsername(username); // 비밀번호
        
        model.addAttribute("id", longId);
        model.addAttribute("username", username);
        model.addAttribute("nickname", nickname);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        
        System.out.println(model);
        // 1111이 password와 같으면 true, 다르면 false
        // System.out.println(passwordEncoder.matches("1111", password));
        
        return "mypage";
    }
    
    // 마이페이지 업로드
    @GetMapping("/mypageupload")
    public String mypageupload() {
        
        return "mypageupload";
    }

	// 어드민 페이지
//    @GetMapping("/admin")
//    public String dispAdmin() {
//          
//        return "admin";
//    }
	
    // 테스트 페이지
    @GetMapping("/test")
    public String test() {
          
        return "test";
    }
    
    // 아이디 찾기 페이지
    @GetMapping("/findId")
    public String findId() {
          
        return "findId";
    }
    
    // 아이디 찾기 기능
    @PostMapping("/findId/findId.do")
    public String find_id(HttpServletResponse response, 
            @RequestParam("email") String email, Model model) throws Exception {
        
        model.addAttribute("id", userService.findUsernameByEmail(response, email));
        return "findIdResult";
    }
    
    // 비밀번호 찾기 기능
    @RequestMapping(value = "/findPw/findPw.do", method = RequestMethod.POST)
    public String find_pw(@RequestParam(value = "username") String username,
                          @RequestParam(value = "email") String email,
                          HttpServletResponse response) throws Exception {
        
        userService.find_pw(response, username, email);
        
        return "findPwResult";
    }

    // 비밀번호 변경 페이지
    @GetMapping(value = "/changePw")
    public String changePw() {
        
        return "changePassword";
    }
    
    // 비밀번호 변경 기능
    @RequestMapping(value = "/changePw/changePw.do", method = RequestMethod.POST)
    public String change_pw(@RequestParam(value = "password") String password,
                          // redirect 할 때 파라미터 값을 가지고 가야할 경우에는 RedirectAttributes를 사용한다.
                          RedirectAttributes redirect, Principal principal) throws Exception {
        
        String username = principal.getName();
        userService.changePw(username, password);
        
        return "redirect:/closet/mypage";
    }
    
    // 회원탈퇴 페이지
    @GetMapping(value = "/withdrawal")
    public String withdrawal_form() {
        
        return "withdrawal";
    }
    
    // 회원탈퇴 기능
    @RequestMapping(value = "/withdrawal/withdrawal.do", method = RequestMethod.POST)
    public String withdrawal(@RequestParam(value = "password") String password,
            // redirect 할 때 파라미터 값을 가지고 가야할 경우에는 RedirectAttributes를 사용한다.
            RedirectAttributes redirect, Principal principal, HttpServletResponse response) throws Exception {
        
        String username = principal.getName();
        userService.withdrawal(username, password, response);
        
        return "redirect:/closet/logout";
    }
    
    
    // QnA 페이지
    @RequestMapping(value = "/qna", method = RequestMethod.GET)
    public String qna() {
        
        return "qna";
    }
    
}   
