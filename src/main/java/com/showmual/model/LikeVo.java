package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LikeVo {
    
    private int likeNo;
    private int boardNo;
    private Long userId;
    private int likeNum;
    
}
