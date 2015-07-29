package net.sls.mobile.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.mobile.product.Broadcast;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.service.mobile.product.IBroadcastService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * @author dyh 首页数据展示
 *
 */
@Controller
public class MainController {
	//M站首页
	@NoNeedUserLogin
	@RequestMapping(value = "index.htm")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("index");
		ReqBo reqBo = new ReqBo(request);
		//用户所在的省市
		Integer provinceId = reqBo.getParamInt("provinceId");
		Integer cityId = reqBo.getParamInt("cityId");
		String cityCode = reqBo.getParamStr("cityCode");
		if (cityId != null && provinceId != null && cityCode != null) {
			SessionUtil.replace(Constants.SESSION_PROVINCEID, provinceId);
			SessionUtil.replace(Constants.SESSION_CITYID, cityId);
			SessionUtil.replace(Constants.SESSION_CITYCODE, cityCode);
		}
		//轮播图
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		reqBo.setParam("channelId", 2);
		IBroadcastService bs = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_BROADCAST, IBroadcastService.class);
		ResBo<List<Broadcast>> resBo = bs.getBroadcastList(reqBo);
		List<Broadcast> broadcast = resBo.getResult(); 
		view.addObject("broadcast", broadcast);
		return view;
	}
	
	//城市选择页
	@NoNeedUserLogin
	@RequestMapping(value = "go_city.htm")
	public ModelAndView goCity(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("common/go_city");
		return view;
	}
	
	//分类页
	@NoNeedUserLogin
	@RequestMapping(value = "category.htm")
	public ModelAndView category(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("common/category");
		return view;
	}
	
	//联系我们页
	@NoNeedUserLogin
	@RequestMapping(value = "contact_we.htm")
	public ModelAndView contactWe(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("contact_we");
		return view;
	}
}
