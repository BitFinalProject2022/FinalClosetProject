package com.showmual.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailDto {
    
    private String email;
    private String title;
    private String content;
}
