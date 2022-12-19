package com.showmual.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.showmual.model.BigCategoryVo;
import com.showmual.model.ClosetVo;
import com.showmual.model.SmallCategoryVo;

@Mapper
@Repository("closetDao")
public interface ClosetDao {
    
    public List<BigCategoryVo> selectBigCategory() throws DataAccessException;
    
    public List<SmallCategoryVo> selectSmallCategory(int big_category_code) throws DataAccessException;
    
    public List<ClosetVo> selectImagePath(int bigCategoryCode, int smallCategoryCode, Long userId) throws DataAccessException;
}
