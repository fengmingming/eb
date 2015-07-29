package net.sls.pc.client.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.pc.product.CommodityDto;
import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.product.IGoodsService;
import net.sls.service.pc.user.ITopNService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 团购列表
 * @author 王国俊
 *
 */
@Controller
@RequestMapping("activity")
public class ActivityController {
	
	/**
	 * 团购列表页的实现
	 * @param request
	 * @return
	 * @author 王国俊
	 */
	
	private ModelAndView getActivityList(ReqBo reqBo) {
		
		ModelAndView view = new ModelAndView("activity/group_purcse");
		
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		//reqBo.setParam("actType", Constants.SESSION_GROUPON);
		
			
		int num = Constants.GOODS_ACTIVITY_NUM_PAGE;
		reqBo.setParam("num", num);
		
		if (reqBo.getParam("currPage") == null || !reqBo.getParam("currPage").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("currPage", 1);
		}
		
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		ResBo<Pager<List<CommodityDto>>> r = is.selectProductPageList(reqBo);
		Pager<List<CommodityDto>> p =  r.getResult();
		List<CommodityDto> l = p.getEntry();
		
		long total_num = p.getTotal();
		long total_page = (total_num + num - 1) / num;
		
		if (total_num > 0) {
			if (reqBo.getParamLong("currPage") > total_page) {//如果当前超过总页数，则默认跳到最后一页
				reqBo.setParam("currPage", total_page);
				l = is.selectProductPageList(reqBo).getResult().getEntry();
			}
		} else {
		}
		
		//人气商品
		ReqBo reqBo4TopN = new ReqBo();
		reqBo4TopN.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		ITopNService tns = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_TOPN, ITopNService.class);
		ResBo<List<Map<Object,Object>>> rb = tns.selectTopNGoodsByCityId(reqBo4TopN);
		List<Map<Object, Object>> topGoods = rb.getResult();
		
		view.addObject("topGoods", topGoods);
		view.addObject("goodslist", l);
		view.addObject("currPage", reqBo.getParam("currPage"));
		view.addObject("total_page", total_page);
		view.addObject("areaId", reqBo.getParam("areaId"));
		view.addObject("actType", reqBo.getParam("actType"));
		return view;
	}
	
	@NoNeedUserLogin
	@RequestMapping(value = "getGrouponList.htm")
	public ModelAndView getGrouponList(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("actType", Constants.SESSION_GROUPON);
		
		return getActivityList(reqBo);
	}
	
	@NoNeedUserLogin
	@RequestMapping(value = "getFlashSaleList.htm")
	public ModelAndView getFlashSaleList(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("actType", Constants.SESSION_FLASHSALE);
		
		return getActivityList(reqBo);
	}

}
