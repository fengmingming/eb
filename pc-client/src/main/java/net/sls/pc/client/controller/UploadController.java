package net.sls.pc.client.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sls.pc.client.annotation.NoNeedUserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadController {
	private String innPath = "upload/";

	@NoNeedUserLogin
	@RequestMapping("/pg.htm")
	public String show(){
		return "alipay/upload";
	}

	@NoNeedUserLogin
	@RequestMapping(value = "/to.htm",method = RequestMethod.POST)
	public ModelAndView fileUpload( @RequestParam(value="multipartFile") MultipartFile multipartFile , HttpServletRequest request, ModelMap model){
		ModelAndView mav = new ModelAndView();
		try{		
			if(request instanceof MultipartHttpServletRequest && !multipartFile.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath(".")+"/WEB-INF/v/"+innPath;	   				
				File folder = new File(path);    
		        if(!folder.exists()){        	
		        	folder.mkdirs();        	
		        } 		        
		       
		        //new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String fileName = multipartFile.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				fileName = df.format(new Date()) + new Random().nextInt(100000) +suffix;

		        File target = new File(path,fileName);
		        target.createNewFile();
		        multipartFile.transferTo(target);
		        mav.addObject("ok","");
		        mav.setViewName("xxx/succeed");
			}
			else{
				mav.addObject("err","上传文件失败，所选择文件为空。");
				mav.setViewName("/err");
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return mav;		
	   
	}
}
