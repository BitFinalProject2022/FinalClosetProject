package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVo {
    
    private int noticeNo;
    private String title;
    private String contents;
    private String writeDate;
    
}
