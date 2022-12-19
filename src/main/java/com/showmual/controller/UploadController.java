package com.showmual.controller;

import java.io.File;
import java.security.Principal;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.showmual.entity.closet.ClosetEntity;
import com.showmual.entity.imageClassification.ImageClothRepository;
import com.showmual.service.ClosetService;
import com.showmual.service.ImageClothService;
import com.showmual.service.UserService;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping(value = "/closet")
public class UploadController {
	
	@Autowired
	ClosetService closetService;
	@Autowired
	UserService userService;
	@Autowired
	ImageClothService imageClothService;
	
	// 사진 등록 페이지
	@RequestMapping("/upload")
	public String Insert() {
		
		return "upload";
	}
	
	// 분석하기 - HashMap 사용 (JSON 리턴받기), 이미지 저장  
	@RequestMapping(value = "/analysis", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> analysisImage(@RequestPart MultipartFile files, Principal principal) throws Exception {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    
	    // 로그인한 사용자의 username을 가져와서 id(int)를 얻는다.
	    String username = principal.getName();
	    String id = userService.findIdByUsername(username);
	    
	    if(!files.isEmpty()) { // 업로드할 파일이 존재할 경우에만
//	        String sourceFileName = files.getOriginalFilename(); 
//	        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
	        File destinationFile;
	        String destinationFileName;
	        // 경로
	        String fileUrl = "E:/Project/images/trash/" + id + "/"; // 아이디 번호로 폴더 생성
	        
	        do {
	            // 이미지 이름
	            destinationFileName = 
//	                    RandomStringUtils.randomAlphanumeric(16) + "_" + id + "." + sourceFileNameExtension; 
        	            RandomStringUtils.randomAlphanumeric(16) + "_" + id + ".png"; 
	            destinationFile = new File(fileUrl + destinationFileName); 
	        } while (destinationFile.exists());

	        // destinationFile 경로로 폴더 생성
	        destinationFile.getParentFile().mkdirs(); 
	        files.transferTo(destinationFile);
	        
	        String imgUrl = fileUrl + destinationFileName; // 이미지 full_path
	        System.out.println(imgUrl);
	        
            // Django로 데이터 보내기
            ImageClothRepository response = imageClothService.getFirstTodoTest(imgUrl);
            System.out.println("=================================");
            System.out.println(response.getImage());
            System.out.println(response.getSmall_category());
            System.out.println(response.getBig());
            System.out.println(response.getQty());

            map.put("image", response.getImage());
            map.put("small_category", response.getSmall_category());
            map.put("big", response.getBig());
            map.put("qty", response.getQty());
            
	        
	    }
	    return map; //스프링이 자동으로 JSON타입으로 반환해서 전달한다.
	    
	}
	
	// 사진 옷장(closet_tbl)에 등록하기
	@RequestMapping(value = "/registImage", method = RequestMethod.POST)
    public String registImage(@RequestPart MultipartFile files, Principal principal, 
                // redirect 할 때 파라미터 값을 가지고 가야할 경우에는 RedirectAttributes를 사용한다.
                RedirectAttributes redirect,
                @RequestParam(value = "big") int bigCategory,
                @RequestParam(value = "qty") int qty
            ) throws Exception {
        
	    ClosetEntity closetEntity = new ClosetEntity();
	    
        // 로그인한 사용자의 username을 가져와서 id(Long)를 얻는다.
        String username = principal.getName();
        String id = userService.findIdByUsername(username);
        Long longId = Long.parseLong(id);  // id를 Integer형으로 바꾼다.
        
        if(!files.isEmpty()) { // 업로드할 파일이 존재할 경우에만
//            String sourceFileName = files.getOriginalFilename(); 
//            String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
            File destinationFile; 
            String destinationFileName;
            // 경로
            String fileUrl = "E:/Project/images/closet/" + id + "/"; // 아이디 번호로 폴더 생성
            
            do {
                // 이미지 이름
                destinationFileName = 
//                        RandomStringUtils.randomAlphanumeric(16) + "_" + id + "." + sourceFileNameExtension; 
                        RandomStringUtils.randomAlphanumeric(16) + "_" + id + ".png"; 
                destinationFile = new File(fileUrl + destinationFileName);
            } while (destinationFile.exists());
            
            // destinationFile 경로로 폴더 생성
            destinationFile.getParentFile().mkdirs(); 
            files.transferTo(destinationFile);
            
            // DB에 넣기
            closetEntity.setUserId(longId);
            closetEntity.setBigCategoryCode(bigCategory);
            closetEntity.setSmallCategoryCode(qty);
            closetEntity.setImagePath(fileUrl);
            closetEntity.setImageName(destinationFileName);
            
            closetService.save(closetEntity);
        }
	    
        return "redirect:upload";
    }
	
	
}
