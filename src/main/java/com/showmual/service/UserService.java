package com.showmual.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.showmual.entity.user.UserEntity;
import com.showmual.entity.user.UserRepository;
import com.showmual.model.Role;
import com.showmual.model.UserDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	
	private MailService mailService;
	
	PasswordEncoder passwordEncoder;

	// 회원가입 기능
    @Transactional
    public Long joinUser(UserDto userDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        return userRepository.save(userDto.toEntity()).getId();
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByUsername(username);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        if (("admin").equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
    
    // 회원가입 시 발생할 수 있는 오류들 관련
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        
        return validatorResult;
    }
    
    // 아이디로 회원 번호(PK) 찾기
	public String findIdByUsername(String username) {
		return userRepository.findIdByUsername(username);
	}
	
	// 아이디로 닉네임 찾기
    public String findNicknameByUsername(String username) {
        return userRepository.findNicknameByUsername(username);
    }
    
    // 아이디로 이메일 찾기
    public String findEmailByUsername(String username) {
        return userRepository.findEmailByUsername(username);
    }
    
    // 아이디로 비밀번호 찾기
    public String findPasswordByUsername(String username) {
        return userRepository.findPasswordByUsername(username);
    }
    
	// 중복 아이디 찾기
	public Long countByUsername(String username) {
	    return userRepository.countByUsername(username);
	}
	
	// 중복 닉네임 찾기
	public Long countByNickname(String nickname) {
	    return userRepository.countByNickname(nickname);
	}
	
	// 이메일로 아이디 찾기
	public String findUsernameByEmail(HttpServletResponse response, String email) throws Exception {
	    
	    response.setContentType("text/html;charset=utf-8");
	    
        PrintWriter out = response.getWriter();
        String username = userRepository.findUsernameByEmail(email);
        
        if (username == null) {
            out.println("<script>");
            out.println("alert('가입된 아이디가 없습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            
            return null;
        } else {
            return username;
        }
	}
	
	// 비밀번호 찾기
    public void find_pw(HttpServletResponse response, String username, String email) throws Exception {
        
        response.setContentType("text/html;charset=utf-8");
        
        PrintWriter out = response.getWriter();
        
        // 아이디가 없으면
        if(userRepository.countByUsername(username) == 0) {
            out.println("<script>");
            out.println("alert('올바르지 않은 아이디입니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
        }
        
        else {
            
            // 임시 비밀번호 생성
            String pw = "";
            for (int i = 0; i < 12; i++) {
                pw += (char) ((Math.random() * 26) + 97);
            }
            
            String id = userRepository.findIdByUsername(username);
            Long longId = Long.parseLong(id);
            
            // 비밀번호 변경, 비밀번호는 암호화해서 DB에 넣는다.
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encoded_pw = bCryptPasswordEncoder.encode(pw);
            
            userRepository.updatePasswordById(encoded_pw, longId);
            
            String title = "[7777] 임시 비밀번호 관련 메일입니다.";
            String content = "회원님의 임시 비밀번호는 " + pw + " 입니다.";
            
            // 임시 비밀번호 메일 발송
            mailService.sendSimpleMessage(email, title, content);
        }
    }
    
    // 비밀번호 변경
    public void changePw(String username, String password) throws Exception {
        
        String id = userRepository.findIdByUsername(username);
        Long longId = Long.parseLong(id);
        
        // 비밀번호 변경, 비밀번호는 암호화해서 DB에 넣는다.
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded_pw = bCryptPasswordEncoder.encode(password);
        
        userRepository.updatePasswordById(encoded_pw, longId);
    }
    
    // 회원 탈퇴
    public void withdrawal(String username, String password, HttpServletResponse response) throws Exception {
        
        response.setContentType("text/html;charset=utf-8");
        
        PrintWriter out = response.getWriter();
        
        String id = userRepository.findIdByUsername(username);
        Long longId = Long.parseLong(id);

        String pw = userRepository.findPasswordByUsername(username);
        
        if(!passwordEncoder.matches(password, pw)) {
            out.println("<script>");
            out.println("alert('비밀번호가 올바르지 않습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            
        } else {
            
            userRepository.deleteById(longId);
        }
    }
    
}
