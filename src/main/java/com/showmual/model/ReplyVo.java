package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyVo {
    
    private int rno;
    private int bno;
    private Long userId;
    private String nickname;
    private String username;
    private String content;
    private String regDate;
    
}
