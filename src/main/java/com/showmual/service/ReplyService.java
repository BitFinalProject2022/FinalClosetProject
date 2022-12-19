package com.showmual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showmual.dao.ReplyDao;
import com.showmual.model.ReplyVo;

@Service("replyService")
@Transactional
public class ReplyService {
    
    @Autowired
    ReplyDao replyDao;
    
    public List<ReplyVo> replyList(int boardNo){
        List<ReplyVo> replyVo = replyDao.replyList(boardNo);
        return replyVo;
    }
    
    public void replyWrite(ReplyVo replyVo) {
        replyDao.replyWrite(replyVo);
    }
    
    public void replyModify(ReplyVo replyVo) {
        replyDao.replyModify(replyVo);
    }
    
    public void replyDelete(int rno) {
        replyDao.replyDelete(rno);
    }
}
