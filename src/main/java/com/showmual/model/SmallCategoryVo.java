package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class SmallCategoryVo {
    
    public int small_category_code;
    public String small_category_name;
    public int big_category_code;
}
