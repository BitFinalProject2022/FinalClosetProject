package com.showmual.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showmual.dao.QnaDao;
import com.showmual.model.QnaVo;


@Service("qnaService")
@Transactional
public class QnaService {
    
    @Autowired
    QnaDao qnaDao;
    
    @Autowired
    private MailService mailService;
    
    
    public List<QnaVo> listQnA(){
        List<QnaVo> qnaList = qnaDao.selectAllQnA();
        return qnaList;
    }
    
    public QnaVo viewQnA(int qnaNo) {
        QnaVo qnaVo;
        qnaVo = qnaDao.selectQnA(qnaNo);
        return qnaVo;
    }
    
    public void addQnA(QnaVo qnaVo) {
        qnaDao.insertNewQnA(qnaVo);
    }
    
    public void editQnA(QnaVo qnaVo) {
        qnaDao.updateQnA(qnaVo);
    }
    
    public void removeQnA(int qnaNo) {
        qnaDao.deleteQnA(qnaNo);
    }
    
    
    // QnA 답변 보내기
    public void answerQnA(String content, String email, String nickname) throws Exception {
        
        String title = "[7777] " + nickname + "님, 문의주신 내용에 대한 답변입니다.";
        
        // 임시 비밀번호 메일 발송
        mailService.sendSimpleMessage(email, title, content);
    }
    
}
