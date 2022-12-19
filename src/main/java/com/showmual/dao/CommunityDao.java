package com.showmual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.showmual.model.BoardVo;
import com.showmual.model.LikeVo;
import com.showmual.model.TotalVo;

@Mapper
@Repository("communityDao")
public interface CommunityDao { // Dao 기능을 불러오는 장소, interface로 선언
	
	public List<TotalVo> selectAllArticles() throws DataAccessException;
	public TotalVo selectArticle(int boardNo) throws DataAccessException;
	
	public void insertNewArticle(BoardVo boardVo) throws DataAccessException;
	public void updateArticle(BoardVo boardVo) throws DataAccessException;
	public void deleteArticle(int boardNo) throws DataAccessException;
	
	// 좋아요 기능
	public int findLike(int boardNo, Long userId) throws DataAccessException;
	public int countLike(int boardNo) throws DataAccessException;
	public void likeUp(LikeVo likeVo) throws DataAccessException;
	public void likeDown(int boardNo, Long userId) throws DataAccessException;
	
}
