package com.showmual.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {

    // 이메일 발송
    private JavaMailSender emailSender;
    
    public void sendSimpleMessage(String email, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom("rudtjq781@naver.com");
        message.setTo(email);
        message.setSubject(title);
        message.setText(content);
        
        emailSender.send(message);
    }
	
}
