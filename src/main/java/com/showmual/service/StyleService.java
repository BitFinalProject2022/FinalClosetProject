package com.showmual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showmual.dao.StyleDao;
import com.showmual.model.ManStyleVo;
import com.showmual.model.WomanStyleVo;

@Service("styleService")
@Transactional
public class StyleService {
    
    @Autowired
    StyleDao styleDao;
    
    public List<ManStyleVo> listManStyles(
            String temperature, String colorCode,
            String styleCode, String hashtagCode) {
        
        String[] temperatureList = null;
        String[] colorCodeList = null;
        String[] styleCodeList = null;
        String[] hashtagCodeList = null;
        
        // 배열로 바꾸기
        if(temperature != "") {
            temperatureList = temperature.split(" ");
        }else if(temperature == "") {
            temperatureList = null;
        }
        if(colorCode != "") {
            colorCodeList = colorCode.split(" ");
        }else if(colorCode == "") {
            colorCodeList = null;
        }
        if(styleCode != "") {
            styleCodeList = styleCode.split(" ");
        }else if(styleCode == "") {
            styleCodeList = null;
        }
        if(hashtagCode != "") {
            hashtagCodeList = hashtagCode.split(" ");
        }else if(hashtagCode == "") {
            hashtagCodeList = null;
        }
        
        List<ManStyleVo> manStyleList = 
                styleDao.selectManCoordinate(temperatureList, colorCodeList, styleCodeList, hashtagCodeList);
        
        return manStyleList;
    }
    
    
    public List<WomanStyleVo> listWomanStyles(
            String temperature, String colorCode,
            String styleCode, String hashtagCode) {
        
        String[] temperatureList = null;
        String[] colorCodeList = null;
        String[] styleCodeList = null;
        String[] hashtagCodeList = null;
        
        // 배열로 바꾸기
        if(temperature != "") {
            temperatureList = temperature.split(" ");
        }else if(temperature == "") {
            temperatureList = null;
        }
        if(colorCode != "") {
            colorCodeList = colorCode.split(" ");
        }else if(colorCode == "") {
            colorCodeList = null;
        }
        if(styleCode != "") {
            styleCodeList = styleCode.split(" ");
        }else if(styleCode == "") {
            styleCodeList = null;
        }
        if(hashtagCode != "") {
            hashtagCodeList = hashtagCode.split(" ");
        }else if(hashtagCode == "") {
            hashtagCodeList = null;
        }
        
        List<WomanStyleVo> womanStyleList = 
                styleDao.selectWomenCoordinate(temperatureList, colorCodeList, styleCodeList, hashtagCodeList);
        
        return womanStyleList;
    }
    
}
