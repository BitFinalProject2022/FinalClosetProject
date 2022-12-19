package com.showmual.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class DiaryVo {
	
	private int diaryNo;
	private String title;
	private Long userId;
	private String writeDate;
	private String contents;
	private String imagePath;
	private String imageName;
	
}
