package com.showmual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.showmual.model.NoticeVo;

@Mapper
@Repository("noticeDao")
public interface NoticeDao {
    
    public List<NoticeVo> selectAllNotice() throws DataAccessException;
    public NoticeVo selectNotice(int noticeNo) throws DataAccessException;
    
    public void insertNewNotice(NoticeVo noticeVo) throws DataAccessException;
    public void updateNotice(NoticeVo noticeVo) throws DataAccessException;
    public void deleteNotice(int noticeNo) throws DataAccessException;
    
}
