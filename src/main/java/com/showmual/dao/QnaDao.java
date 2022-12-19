package com.showmual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.showmual.model.QnaVo;

@Mapper
@Repository("qnaDao")
public interface QnaDao {
    
    public List<QnaVo> selectAllQnA() throws DataAccessException;
    public QnaVo selectQnA(int qnaNo) throws DataAccessException;
    
    public void insertNewQnA(QnaVo qnaVo) throws DataAccessException; 
    public void updateQnA(QnaVo qnaVo) throws DataAccessException; 
    public void deleteQnA(int qnaNo) throws DataAccessException; 

}
