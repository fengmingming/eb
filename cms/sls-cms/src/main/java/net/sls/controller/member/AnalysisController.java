package net.sls.controller.member;

import net.sls.service.user.IMemberService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("analysis")
public class AnalysisController {
	
	@RequestMapping("user.htm")
	@ResponseBody
	public ResBo<?> analysisUser(@RequestParam("userId")long userId){
		IMemberService is = FindServiceUtil.findService(IMemberService.class);
		return is.analysisUser(new ReqBo().setParam("userId", userId));
	}

}
