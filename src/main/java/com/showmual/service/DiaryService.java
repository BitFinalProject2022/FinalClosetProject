package com.showmual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showmual.dao.DiaryDao;
import com.showmual.model.DiaryVo;

@Service("diaryService")
@Transactional
public class DiaryService {
    
	@Autowired
	DiaryDao diaryDao;
	
	public List<DiaryVo> listDiarys(Long userId){
		List<DiaryVo> diaryList = diaryDao.selectAllDiarys(userId);
		return diaryList;
	}
	
	public DiaryVo viewDiary(int diaryNo) {
        DiaryVo diary;
        diary = diaryDao.selectDiary(diaryNo);
        return diary;
    }
	
	public void addDiary(DiaryVo diaryVo) {
		diaryDao.insertNewDiary(diaryVo);
	}
	
	public void editDiary(DiaryVo diaryVo) {
	    diaryDao.updateDiary(diaryVo);
    }
    
    public void removeDiary(int diaryNo) {
        diaryDao.deleteDiary(diaryNo);
    }
}
