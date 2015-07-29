package net.sls.pc.client.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前端意见反馈功能
 *
 */
@Controller
@RequestMapping("user")
public class FeedBackController {
	/**
	 * 进入意见反馈页
	 */
	@RequestMapping(value = "toFeedBack.htm")
	public ModelAndView toFeedBack() {
		ModelAndView view =  new ModelAndView("user/feedback");
		return view;
	}
}
