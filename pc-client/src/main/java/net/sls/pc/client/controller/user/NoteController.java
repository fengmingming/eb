package net.sls.pc.client.controller.user;

import javax.servlet.http.HttpServletRequest;

import net.sls.pc.client.annotation.NoNeedUserLogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author dyh 网站底部链接
 *
 */
@Controller
@RequestMapping("note")
public class NoteController {
	
	/**
	 * 网站foot链接的页面
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "show.htm")
	public ModelAndView convenientMoney(HttpServletRequest request, String menuId) {
		ModelAndView view = new ModelAndView("note/" + menuId);
		view.addObject("menuId", menuId);
		return view;
	}
	
	/**
	 * 用户注册协议
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "registAgreement.htm")
	public ModelAndView registAgreement(HttpServletRequest request, String menuId) {
		ModelAndView view = new ModelAndView("regist/regist_agreement");
		return view;
	}
}
