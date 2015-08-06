package net.sls.mobile.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.act.Dynpage;
import net.sls.dto.pc.product.CommodityDto;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.service.pc.act.IDynpageService;
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
 * 团购列表
 * @author huzeyu 2015-04-14
 *
 */
@Controller
@RequestMapping("activity")
public class ActivityController {
	/**
	 * 商品闪购(团购)列表
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getGrouponList.htm")
	public ModelAndView getGrouponList() {
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("actType", Constants.SESSION_GROUPON);
		ModelAndView view = new ModelAndView("activity/group_flash_list");
		view.addObject("actType", Constants.SESSION_GROUPON);
		ActEnum act = ActEnum.getActEnum(Constants.SESSION_GROUPON);
		view.addObject("actTypeName", act!=null?act.getName():"");
		return view;
	}
	
	/**
	 * 限时抢购列表
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getFlashSaleList.htm")
	public ModelAndView getFlashSaleList() {
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("actType", Constants.SESSION_FLASHSALE);
		ModelAndView view = new ModelAndView("activity/group_flash_list");
		view.addObject("actType", Constants.SESSION_FLASHSALE);
		ActEnum act = ActEnum.getActEnum(Constants.SESSION_FLASHSALE);
		view.addObject("actTypeName", act!=null?act.getName():"");
		return view;
	}
	
	/**
	 * 商品闪购(团购)/限时抢购列表页数据
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@ResponseBody
	@RequestMapping(value = "getGFGoodsList.htm")
	public ResBo<Pager<List<CommodityDto>>> getGroupAndFlashGoodsList(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		int num = Constants.GOODS_ACTIVITY_NUM_PAGE;
		reqBo.setParam("num", num);
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		return is.selectProductPageList(reqBo);
	}
	
	/**
	 * 进入市场活动商品列表页
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getDynpageGoodsList.htm")
	public ModelAndView getDynpageGoodsList(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		ModelAndView view = new ModelAndView("activity/goodsDynpage_list");
	
		int num = Constants.GOODS_NUM_PAGE;
		reqBo.setParam("num", num);
		if (reqBo.getParam("currPage") == null || !reqBo.getParam("currPage").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("currPage", 1);
		}
		
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		ResBo<Pager<List<CommodityDto>>> r = is.selectDynpageGoodsPageList(reqBo);
		Pager<List<CommodityDto>> p =  r.getResult();
		List<CommodityDto> l = p.getEntry();
		
		long total_num = p.getTotal();
		long total_page = (total_num + num - 1) / num;
		
		if (total_num > 0) {
			if (reqBo.getParamLong("currPage") > total_page) {//如果当前超过总页数，则默认跳到最后一页
				reqBo.setParam("currPage", total_page);
				l = is.selectDynpageGoodsPageList(reqBo).getResult().getEntry();
			}
			view.addObject("goodslist", l);
			view.addObject("currPage", reqBo.getParam("currPage"));
			view.addObject("total_page", total_page);
			view.addObject("dynpageId", reqBo.getParamInt("dynpageId"));
		} else {
			view.addObject("goodslist", null);
			view.addObject("currPage", reqBo.getParam("currPage"));
			view.addObject("total_page", 0l);
			view.addObject("dynpageId", reqBo.getParamInt("dynpageId"));
			//前台页面直接显示“空列表消息提示框”
		}
		// 市场节日活动title
		IDynpageService ids= FindServiceUtil.findRemoteService(Constants.SERVICE_URL_Dynpage, IDynpageService.class);
		ResBo<Dynpage> resBoDynapge = ids.selectByPrimaryKey(reqBo);
		Dynpage dy = resBoDynapge.getResult();
		view.addObject("title", dy.getTitle());
		return view;
	}
	/**
	 * 商品列表(ajax)
	 * @param request
	 * @param response
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getDynpageGoodsAjax.htm")
	@ResponseBody
	public Pager<List<CommodityDto>> getDynpageGoodsAjax(HttpServletRequest request, HttpServletResponse response) {
		ReqBo reqBo = new ReqBo(request);
		
		int num = Constants.GOODS_NUM_PAGE;
		reqBo.setParam("num", num);
		if (reqBo.getParam("currPage") == null || !reqBo.getParam("currPage").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("currPage", 1);
		}
		
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		ResBo<Pager<List<CommodityDto>>> r = is.selectDynpageGoodsPageList(reqBo);
		Pager<List<CommodityDto>> p =  r.getResult();
		if(p == null){
			return null;
		}else{
			long total_num = p.getTotal();
			long total_page = (total_num + num - 1) / num;
			if (total_num > 0) {
				if (reqBo.getParamLong("currPage") > total_page) {//如果当前超过总页数，则默认跳到最后一页
					p.setEntry(null);
				}
			}
		}
		return p;
	}
}