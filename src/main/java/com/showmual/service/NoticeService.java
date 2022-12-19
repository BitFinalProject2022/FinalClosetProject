package com.showmual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showmual.dao.NoticeDao;
import com.showmual.model.NoticeVo;

@Service("noticeService")
@Transactional
public class NoticeService {
    
    @Autowired
    NoticeDao noticeDao;
    
    public List<NoticeVo> listNotice() {
        List<NoticeVo> noticeList = noticeDao.selectAllNotice();
        return noticeList;
    }
    
    public NoticeVo viewNotice(int noticeNo) {
        NoticeVo notice;
        notice = noticeDao.selectNotice(noticeNo);
        return notice;
    }
    
    public void addNotice(NoticeVo noticeVo) {
        noticeDao.insertNewNotice(noticeVo);
    }
    
    public void editNotice(NoticeVo noticeVo) {
        noticeDao.updateNotice(noticeVo);
    }
    
    public void removeNotice(int noticeNo) {
        noticeDao.deleteNotice(noticeNo);
    }
    
}
