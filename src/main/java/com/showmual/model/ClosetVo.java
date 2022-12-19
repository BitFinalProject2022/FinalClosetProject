package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ClosetVo {
    
    public int itemId;
    public Long userId;
    public int bigCategoryCode;
    public int smallCategoryCode;
    public String imagePath;
    public String imageName;
}
