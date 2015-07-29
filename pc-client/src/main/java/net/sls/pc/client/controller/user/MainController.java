package net.sls.pc.client.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.pc.product.Broadcast;
import net.sls.dto.pc.product.Category;
import net.sls.dto.pc.product.CommodityDto;
import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.product.IBroadcastService;
import net.sls.service.pc.product.ICategoryService;
import net.sls.service.pc.product.IGoodsService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.model.ActEnum;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * @author dyh 首页数据展示功能
 *
 */
@Controller
@RequestMapping("main")
public class MainController {
	@NoNeedUserLogin
	@RequestMapping(value = "index.htm")
	public ModelAndView index(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		ModelAndView view = new ModelAndView("index");
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
		reqBo.setParam("channelId", 1);
		IBroadcastService bs = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_BROADCAST, IBroadcastService.class);
		ResBo<List<Broadcast>> resBo = bs.getBroadcastList(reqBo);
		List<Broadcast> broadcast = resBo.getResult(); 
		view.addObject("broadcast", broadcast);
		
		
		//团购轮播
		reqBo.setParam("actType", ActEnum.Groupon.getActType());
		int num = Constants.GOODS_NUM_PAGE;
		reqBo.setParam("num", num);
		if (reqBo.getParam("currPage") == null || !reqBo.getParam("currPage").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("currPage", 1);
		}
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		ResBo<Pager<List<CommodityDto>>> r = is.selectProductPageList(reqBo);
		Pager<List<CommodityDto>> p =  r.getResult();
		List<CommodityDto> l = p.getEntry();
		view.addObject("goodslist", l);
		
		return view;
	}
	
	/**
	 * 内页分类导航的二级分类集合获取
	 * @param request
	 * @return
	 * 这个方法是以前在内页分类导航ajax来调用的，现在前台已经去掉了，这个方法先留着
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getSeconds.htm")
	@ResponseBody
	public ResBo<List<Category>> getSeconds(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		ICategoryService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_CATEGORY, ICategoryService.class);
		ResBo<List<Category>> r = is.getCategoryList(reqBo);
		return r;
	}
	
	@NoNeedUserLogin
	@RequestMapping(value = "error.htm")
	public ModelAndView error(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("error");
		return view;
	}
}
