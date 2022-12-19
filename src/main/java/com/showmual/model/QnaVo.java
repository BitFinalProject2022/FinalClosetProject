package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaVo {
    
    private int qnaNo;
    private String email;
    private String username;
    private String title;
    private String content;
    private String writeDate;
    
}
