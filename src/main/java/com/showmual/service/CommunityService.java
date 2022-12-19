package com.showmual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showmual.dao.CommunityDao;
import com.showmual.model.BoardVo;
import com.showmual.model.LikeVo;
import com.showmual.model.TotalVo;

@Service("communityService")
@Transactional
public class CommunityService {
    
	@Autowired
	CommunityDao communityDao;
	
	public List<TotalVo> listArticles(){
		List<TotalVo> articleList = communityDao.selectAllArticles();
		return articleList;
	}
	
	public TotalVo viewArticle(int boardNo) {
	    TotalVo totalVo;
	    totalVo = communityDao.selectArticle(boardNo);
        return totalVo;
    }
	
	public void addArticle(BoardVo boardVo) {
		communityDao.insertNewArticle(boardVo);
	}
	
	public void editArticle(BoardVo boardVo) {
	    communityDao.updateArticle(boardVo);
    }
    
    public void removeArticle(int boardNo) {
        communityDao.deleteArticle(boardNo);
    }
    
    
    // 좋아요 기능
    public int findLike(int boardNo, Long userId) {
        int result = communityDao.findLike(boardNo, userId);
        return result;
    }
    
    public int countLike(int boardNo) {
        int result = communityDao.countLike(boardNo);
        return result;
    }
    
    public void likeUp(LikeVo likeVo) {
        communityDao.likeUp(likeVo);
    }
    
    public void likeDown(int boardNo, Long userId) {
        communityDao.likeDown(boardNo, userId);
    }
    
}
