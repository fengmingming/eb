package net.sls.mobile.client.controller.commons;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.framework.SessionUtil;

import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping("img")
public class ImgValidatorController {

	@Resource
	public Producer captchaProducer;
	
	@NoNeedUserLogin
	@RequestMapping("validate.htm")
	public void getImgValidator(HttpServletResponse response) throws Exception{
		response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        String capText = captchaProducer.createText();
        SessionUtil.replace(Constants.IMG_VALIDATE , capText);
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out); 
        out.flush();
        out.close();
	}
}
