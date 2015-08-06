package net.sls.mobile.client.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.product.CommodityDto;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.service.pc.product.IGoodsService;
import net.sls.service.pc.user.ITopNService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * @author dyh 商品数据集展示
 *
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
	/**
	 * 商品列表
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "goodsList.htm")
	@ResponseBody
	public ResBo<?> goodsList(HttpServletRequest request) {
		Map<String,Object> view = new HashMap<String,Object>();
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		//按一级分类或者二级分类
		if (reqBo.getParam("first") == null && reqBo.getParam("second") == null) {
			reqBo.setParam("first", Constants.SC_ID);//默认按第一个蔬菜类去查找全部的商品
			view.put("first", reqBo.getParam("first"));
			view.put("second", "");
		} else if (reqBo.getParam("first") != null && reqBo.getParam("first").toString().matches("[1-9][0-9]*") && reqBo.getParam("second") == null) {//不排除不合法的数值
			view.put("first", reqBo.getParam("first"));
			view.put("second", "");
		} else if (reqBo.getParam("first") == null && reqBo.getParam("second") != null && reqBo.getParam("second").toString().matches("[1-9][0-9]*")) {//不排除不合法的数值
			view.put("first", "");
			view.put("second", reqBo.getParam("second"));
		} else {//一级分类，二级分类的id值不合法时(比如：字符之类的)
			reqBo.setParam("first", Constants.SC_ID);//默认按第一个蔬菜类去查找全部的商品
			reqBo.setParam("second", null);
			view.put("first", reqBo.getParam("first"));
			view.put("second", "");
		}
		
		//按综合，新品，销量，价格排序
		if (reqBo.getParam("sortTp") == null) {//直接点击分类标签时
			view.put("sortTp", "sort_zh");
		} else if (reqBo.getParam("sortTp") != null) {//点击综合或新品或销量或价格时
			if ("sort_sale".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortSale", 0);
				view.put("sortTp", reqBo.getParam("sortTp"));
			} else if ("sort_price_0".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortPrice", 0);
				view.put("sortTp", reqBo.getParam("sortTp"));
			} else if ("sort_price_1".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortPrice", 1);
				view.put("sortTp", reqBo.getParam("sortTp"));
			} else {//参数不合法时
				reqBo.setParam("sortSale", null);
				reqBo.setParam("sortPrice", null);
				view.put("sortTp", "sort_zh");
			}
		}		
		
		int num = Constants.GOODS_NUM_PAGE;
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
		view.put("goodslist", l);
		view.put("currPage", reqBo.getParam("currPage"));
		view.put("total_page", total_page);
		view.put("areaId", reqBo.getParam("areaId"));
		return new ResBo<Map<String,Object>>(view);
	}
	
	/**
	 * 商品搜索（ajax）
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "goodsListBySearch.htm")
	@ResponseBody
	public ResBo<List<CommodityDto>> goodsListBySearch(HttpServletRequest request) throws UnsupportedEncodingException {
		ReqBo reqBo = new ReqBo(request);
		
		//按城市
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		//按综合，新品，销量，价格
		if (reqBo.getParam("sortTp") != null) {//点击综合或新品或销量或价格时
			if ("sort_sale".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortSale", 0);

			} else if ("sort_price_0".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortPrice", 0);

			} else if ("sort_price_1".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortPrice", 1);

			} else {//参数不合法时
				reqBo.setParam("sortSale", null);
				reqBo.setParam("sortPrice", null);

			}
		}
		
		//按关键字
		if (reqBo.getParam("content") != null && !"".equals(reqBo.getParam("content"))) {
			String content = Constants.parseUTF8((String)reqBo.getParam("content"));
			reqBo.setParam("content", content);
		}
		
		int num = Constants.GOODS_NUM_PAGE;
		reqBo.setParam("num", num);
		
		if (reqBo.getParam("currPage") == null || !reqBo.getParam("currPage").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("currPage", 1);
		}
		List<CommodityDto> l = null;
		if (reqBo.getParam("content") != null && !"".equals(reqBo.getParam("content"))) {
			IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
			ResBo<Pager<List<CommodityDto>>> r = is.sreachGoods(reqBo);	
			if(r.isSuccess()){
				l = r.getResult().getEntry();
			}
		}
		return new ResBo<List<CommodityDto>>(l);
	}
	
	/**
	 * 商品详情页
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "goodsDetail.htm")
	@ResponseBody
	public ResBo<?> goodsDetail(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		Map<String,Object> view = new HashMap<String,Object>();
		getGoodsInfo(reqBo, view);
		return new ResBo<Map<String,Object>>(view);
	}
	
	/**
	 * 商品图文详情页
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "goodsImgTxt.htm")
	@ResponseBody
	public ResBo<?> goodsImgTxt(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		Map<String,Object> view = new HashMap<String,Object>();
		getGoodsInfo(reqBo, view);
		return new ResBo<Map<String,Object>>(view);
	}
	
	/**
	 * 人气商品列表
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "topNList.htm")
	public ModelAndView topNList(){
		ReqBo reqBo = new ReqBo();
		ModelAndView view = new ModelAndView("goods/top_n_list");
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		ITopNService tns = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_TOPN, ITopNService.class);
		ResBo<List<Map<Object,Object>>> rb = tns.selectTopNGoodsByCityId(reqBo);
		if(rb != null && rb.isSuccess()){
			view.addObject("topGoods", rb.getResult());
		}
		return view;
	}

	private void getGoodsInfo(ReqBo reqBo, Map<String,Object> view) {
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		if (reqBo.getParam("id") != null && !reqBo.getParam("id").toString().matches("[0-9]*")) {
			
		} else {
			CommodityDto goods = null;
			ResBo<CommodityDto> rbc = is.selectGoodsDetail(reqBo);
			goods = rbc.getResult();
			if (goods != null) {
				List<String> urls = new ArrayList<String>();
				urls.add(goods.getPhotoUrl());
				urls.add(goods.getPhotoUrl1());
				urls.add(goods.getPhotoUrl2());
				urls.add(goods.getPhotoUrl3());
				urls.add(goods.getPhotoUrl4());
				view.put("goods", goods);
				view.put("urls", urls);
				if (goods.getActDto()!=null) {
					view.put("commodityActDto", goods.getActDto().get(0));
				}
			}
		}
	}
	
	/**
	 * 专区活动
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getOEMAjax.htm")
	@ResponseBody
	public ResBo<Pager<List<CommodityDto>>> getOEMAjax(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		ResBo<Pager<List<CommodityDto>>> r = is.selectDynpageGoodsListByType(reqBo);
		return r;
	}
	
	/**
	 * 专区活动
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getOEM.htm")
	@ResponseBody
	public ModelAndView getOEM(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		ModelAndView view = new ModelAndView("activity/column_list");	
		int num = Constants.GOODS_NUM_PAGE;
		reqBo.setParam("num", num);
		if (reqBo.getParam("currPage") == null || !reqBo.getParam("currPage").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("currPage", 1);
		}
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		ResBo<Pager<List<CommodityDto>>> r = is.selectDynpageGoodsListByType(reqBo);
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
		}else{
			view.addObject("goodslist", null);
			view.addObject("currPage", reqBo.getParam("currPage"));
			view.addObject("total_page", 0l);
			//前台页面直接显示“空列表消息提示框”
		}
		
		view.addObject("titleType", reqBo.getParamInt("type"));
		return view;
	}
	
	/**
	 * 专区活动商品列表(ajax)
	 * @param request
	 * @param response
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getOEMListAjax.htm")
	@ResponseBody
	public Pager<List<CommodityDto>> getDynpageGoodsAjax(HttpServletRequest request, HttpServletResponse response) {
		ReqBo reqBo = new ReqBo(request);
		
		int num = Constants.GOODS_NUM_PAGE;
		reqBo.setParam("num", num);
		if (reqBo.getParam("currPage") == null || !reqBo.getParam("currPage").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("currPage", 1);
		}
		
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		ResBo<Pager<List<CommodityDto>>> r = is.selectDynpageGoodsListByType(reqBo);
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
