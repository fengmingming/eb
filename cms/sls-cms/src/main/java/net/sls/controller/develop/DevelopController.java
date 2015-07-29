package net.sls.controller.develop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * 开发管理
 * 
 * */
@Controller
@RequestMapping("develop")
public class DevelopController {

	@RequestMapping("initNavigater.htm")
	public String initNavigater(){
		return "navigater/develop";
	}
	
}
