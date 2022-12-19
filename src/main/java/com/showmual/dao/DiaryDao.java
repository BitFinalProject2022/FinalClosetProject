package com.showmual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.showmual.model.DiaryVo;

@Mapper
@Repository("diaryDao")
public interface DiaryDao { // Dao 기능을 불러오는 장소, interface로 선언
	
	public List<DiaryVo> selectAllDiarys(Long userId) throws DataAccessException;
	public DiaryVo selectDiary(int diaryNo) throws DataAccessException;
	
	public void insertNewDiary(DiaryVo diaryVo) throws DataAccessException;
	public void updateDiary(DiaryVo diaryVo) throws DataAccessException;
	public void deleteDiary(int diaryNo) throws DataAccessException;
	
}

