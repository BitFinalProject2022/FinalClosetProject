package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManStyleVo {
    
    private int menId;
    private int temperature;
    private String colorName;
    private int colorCode;
    private String styleName;
    private int styleCode;
    private String hashtagName;
    private int hashtagCode;
    private String imagePath;
    private String imageName;
    
}
