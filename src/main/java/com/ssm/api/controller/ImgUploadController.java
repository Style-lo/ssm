package com.ssm.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller  
@RequestMapping("/file") 
public class ImgUploadController {

	@RequestMapping("/upload")  
    @ResponseBody  
    public String test(MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{
		String path = request.getSession().getServletContext().getRealPath("/images");
		System.out.println("path>>"+path);  
		
		String fileName = file.getOriginalFilename();
		System.out.println("fileName>>"+fileName); 
		
		File dir = new File(path, fileName);
		if(!dir.exists()){  
            dir.mkdirs();  
        }  
        System.out.println("dir.exists()>>"+dir.exists());  
//      MultipartFile自带的解析方法  
        file.transferTo(dir);  
          
        return "ok";  
		
	}
	
	
	@RequestMapping(value="/upload2" ,produces="text/html;charset=utf-8" )  
	  public @ResponseBody String importPicFile1(@RequestParam("picParams") String picParams,  
	@RequestParam MultipartFile myfile,HttpServletRequest request){  
	              
	            Map<String,Object> map= new HashMap<String,Object>();  
	             if(myfile.isEmpty()){  
	                  map.put( "result", "error");  
	                  map.put( "msg", "上传文件不能为空" );  
	            } else{  
	                  String originalFilename=myfile.getOriginalFilename();  
	                  String fileBaseName=FilenameUtils.getBaseName(originalFilename);  
	                  String floderName=fileBaseName+"_" +new Date();  
	                   try{  
	                          
	                        String genePicPath=request.getSession().getServletContext().getRealPath("/images/" +floderName);  
	                         //把上传的图片放到服务器的文件夹下  
	                        FileUtils. copyInputStreamToFile(myfile.getInputStream(), new File(genePicPath,originalFilename));  
	                         //coding  
	                        map.put( "result", "success");  
	                          
	                  } catch (Exception e) {  
	                        map.put( "result", "error");  
	                        map.put( "msg",e.getMessage());  
	                          
	                  }  
	            }  
	            String result=String. valueOf(JSONObject.fromObject (map));  
	             return result;  
	      }
}
