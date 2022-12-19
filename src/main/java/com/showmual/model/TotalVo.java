package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TotalVo {
    
    private int boardNo;
    private String title;
    private Long userId;
    private String writeDate;
    private String contents;
    private String imagePath;
    private String imageName;
    private String nickname;
    private String username;
    
}
