package com.showmual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.showmual.model.ReplyVo;

@Mapper
@Repository("replyDao")
public interface ReplyDao {
    
    public List<ReplyVo> replyList(int boardNo) throws DataAccessException;
    
    public void replyWrite(ReplyVo replyVo) throws DataAccessException;
    public void replyModify(ReplyVo replyVo) throws DataAccessException;
    public void replyDelete(int rno) throws DataAccessException;
    
}
