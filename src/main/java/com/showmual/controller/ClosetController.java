package com.showmual.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.showmual.model.BigCategoryVo;
import com.showmual.model.ClosetVo;
import com.showmual.model.SmallCategoryVo;
import com.showmual.service.ClosetService;
import com.showmual.service.UserService;

@Controller("closetController")
@RequestMapping(value = "/closet")
public class ClosetController {
    
    @Autowired
    ClosetService closetService;
    @Autowired
    UserService userService; // 사용자 id 가져오기 위해
    
    
    @Autowired
    BigCategoryVo bigCategoryVo;
    @Autowired
    SmallCategoryVo smallCategoryVo;
    @Autowired
    ClosetVo closetVo;
    
    List<BigCategoryVo> bigCategoryList = new ArrayList<BigCategoryVo>();
    List<SmallCategoryVo> smallCategoryList = new ArrayList<SmallCategoryVo>();
    List<ClosetVo> imagePathList = new ArrayList<ClosetVo>();
    
    // 대분류 항목 가져오기
    @RequestMapping(value="/bigCategoryList", method=RequestMethod.POST)
    @ResponseBody
    public List<BigCategoryVo> selectBigCategory() {
        bigCategoryList = closetService.bigCategoryList();
        
        return bigCategoryList;
    }
    
    // 소분류 항목 가져오기
    @RequestMapping(value="/smallCategoryList", method=RequestMethod.POST)
    @ResponseBody
    public List<SmallCategoryVo> selectSmallCategory(@RequestParam(value = "big_category_code") int big_category_code) {
        smallCategoryList = closetService.smallCategoryList(big_category_code);
        
        return smallCategoryList;
    }
    
    // userId & imageName 가져오기
    @RequestMapping(value="/imagePathList", method=RequestMethod.POST)
    @ResponseBody
    public List<ClosetVo> selectImagePath(
            @RequestParam(value = "small_category_code") int smallCategoryCode,
            @RequestParam(value = "bigCategoryCode") int bigCategoryCode,
                    Principal principal) {
        
        // 로그인한 사용자의 username를 가져와서 id(int)를 얻는다.
        String username = principal.getName();
        String id = userService.findIdByUsername(username);
        Long longId = Long.parseLong(id);  // id를 Integer형으로 바꾼다.
        
        imagePathList = closetService.imagePathList(bigCategoryCode, smallCategoryCode, longId);
        
        return imagePathList;
    }
    
    
    // userId & imageName 가져오기
//    @RequestMapping(value="/imagePathList", method=RequestMethod.POST)
//    @ResponseBody
//    public String selectImagePath(
//            @RequestParam(value = "small_category_code") int smallCategoryCode,
//            @RequestParam(value = "bigCategoryCode") int bigCategoryCode,
//            Principal principal, Model model) {
//        
//        // 로그인한 사용자의 username를 가져와서 id(int)를 얻는다.
//        String username = principal.getName();
//        String id = userService.findIdByUsername(username);
//        Long longId = Long.parseLong(id);  // id를 Integer형으로 바꾼다.
//        
//        imagePathList = closetService.imagePathList(bigCategoryCode, smallCategoryCode, longId);
//        
//        for (ClosetVo c : imagePathList) {
//            System.out.println(c);
//        }
//        
//        model.addAttribute("imagePathList", imagePathList);
//        
//        return "closet";
//    }
    
}
