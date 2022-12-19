package com.showmual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showmual.dao.ClosetDao;
import com.showmual.entity.closet.ClosetEntity;
import com.showmual.entity.closet.ClosetRepository;
import com.showmual.model.BigCategoryVo;
import com.showmual.model.ClosetVo;
import com.showmual.model.SmallCategoryVo;

@Service("closetService")
@Transactional
public class ClosetService {

    @Autowired
    private ClosetRepository closetRepository;
    @Autowired
    private ClosetDao closetDao;
    
    // DB에 저장하는 메소드  --  JPA에서 사용
    public void save(ClosetEntity closetEntity) {
        ClosetEntity c = new ClosetEntity();
        
        c.setUserId(closetEntity.getUserId());
        c.setBigCategoryCode(closetEntity.getBigCategoryCode());
        c.setSmallCategoryCode(closetEntity.getSmallCategoryCode());
        c.setImagePath(closetEntity.getImagePath());
        c.setImageName(closetEntity.getImageName());
        
        closetRepository.save(c);
    }
    
    
    // Closet에 사용할 메소드
    public ClosetService(ClosetDao closetDao) {
        this.closetDao = closetDao;
    }
    
    
    public List<BigCategoryVo> bigCategoryList() {
        List<BigCategoryVo> bigCategory = closetDao.selectBigCategory();
        
        return bigCategory;
    }
    
    public List<SmallCategoryVo> smallCategoryList(int big_category_code) {
        List<SmallCategoryVo> smallCategory = closetDao.selectSmallCategory(big_category_code);
        
        return smallCategory;
    }
    
    public List<ClosetVo> imagePathList(int bigCategoryCode, int smallCategoryCode, Long userId) {
        List<ClosetVo> imagePathList = closetDao.selectImagePath(bigCategoryCode, smallCategoryCode, userId);
        
        return imagePathList;
    }
}
