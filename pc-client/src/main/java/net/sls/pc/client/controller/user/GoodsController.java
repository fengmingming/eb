package net.sls.pc.client.controller.user;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.act.Dynpage;
import net.sls.dto.pc.product.CommodityDto;
import net.sls.dto.pc.product.GoodsCategory;
import net.sls.dto.pc.product.ShopCart;
import net.sls.dto.pc.shopcart.Cart;
import net.sls.dto.pc.user.MyFavorite;
import net.sls.dto.pc.user.User;
import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.util.Constants;
import net.sls.pc.client.util.CookieUtil;
import net.sls.pc.client.util.DateUtils;
import net.sls.service.pc.act.IDynpageService;
import net.sls.service.pc.product.ICategoryService;
import net.sls.service.pc.product.IGoodsService;
import net.sls.service.pc.product.IShopCartService;
import net.sls.service.pc.user.IMyFavoriteService;
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
 * @author dyh 前端商品功能
 *
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
	/**
	 * 进入商品列表页
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getGoodsList.htm")
	public ModelAndView getGoodsList(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		ModelAndView view = new ModelAndView("goods/goods_list");
		
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		//按一级分类或者二级分类
		if (reqBo.getParam("first") == null && reqBo.getParam("second") == null) {
			reqBo.setParam("first", Constants.SC_ID);//默认按第一个蔬菜类去查找全部的商品
			view.addObject("first", reqBo.getParam("first"));
			view.addObject("second", "");
		} else if (reqBo.getParam("first") != null && reqBo.getParam("first").toString().matches("[1-9][0-9]*") && reqBo.getParam("second") == null) {//不排除不合法的数值
			view.addObject("first", reqBo.getParam("first"));
			view.addObject("second", "");
		} else if (reqBo.getParam("first") == null && reqBo.getParam("second") != null && reqBo.getParam("second").toString().matches("[1-9][0-9]*")) {//不排除不合法的数值
			view.addObject("first", "");
			view.addObject("second", reqBo.getParam("second"));
		} else {//一级分类，二级分类的id值不合法时(比如：字符之类的)
			reqBo.setParam("first", Constants.SC_ID);//默认按第一个蔬菜类去查找全部的商品
			reqBo.setParam("second", null);
			view.addObject("first", reqBo.getParam("first"));
			view.addObject("second", "");
		}
		
		//按综合，新品，销量，价格排序
		if (reqBo.getParam("sortTp") == null) {//直接点击分类标签时
			view.addObject("sortTp", "sort_zh");
		} else if (reqBo.getParam("sortTp") != null) {//点击综合或新品或销量或价格时
			if ("sort_new".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortNew", 0);
				view.addObject("sortTp", reqBo.getParam("sortTp"));
			} else if ("sort_sale".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortSale", 0);
				view.addObject("sortTp", reqBo.getParam("sortTp"));
			} else if ("sort_price_0".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortPrice", 0);
				view.addObject("sortTp", reqBo.getParam("sortTp"));
			} else if ("sort_price_1".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortPrice", 1);
				view.addObject("sortTp", reqBo.getParam("sortTp"));
			} else {//参数不合法时
				reqBo.setParam("sortNew", null);
				reqBo.setParam("sortSale", null);
				reqBo.setParam("sortPrice", null);
				view.addObject("sortTp", "sort_zh");
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
		
		if (total_num > 0) {
			if (reqBo.getParamLong("currPage") > total_page) {//如果当前超过总页数，则默认跳到最后一页
				reqBo.setParam("currPage", total_page);
				l = is.selectProductPageList(reqBo).getResult().getEntry();
			}
		} else {//一级分类，二级分类id数值不存在或不合法时
//			reqBo.setParam("first", Constants.SC_ID);//默认按第一个蔬菜类去查找全部的商品
//			reqBo.setParam("second", null);
//			view.addObject("first", reqBo.getParam("first"));
//			view.addObject("second", "");
//			
//			reqBo.setParam("currPage", 1);
//			
//			p = is.selectProductPageList(reqBo).getResult();
//			total_num = p.getTotal();
//			total_page = (total_num + num - 1) / num;	
//			l = p.getEntry();
			
			//前台页面直接显示“空列表消息提示框”
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
		
		return view;
	}
	
	/**
	 * 进入商品详情页
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getGoodsInfo.htm")
	public ModelAndView getGoodsInfo(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		ModelAndView view = new ModelAndView("goods/goods_detail");
		IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
		if (reqBo.getParam("id") != null && !reqBo.getParam("id").toString().matches("[0-9]*")) {
			view = new ModelAndView("index");
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
				view.addObject("goods", goods);
				view.addObject("PhotoUrl", goods.getPhotoUrl());
				view.addObject("urls", urls);
				if (goods.getActDto()!=null) {
					view.addObject("commodityActDto", goods.getActDto().get(0));
					view.addObject("time", DateUtils.timeDifference(goods.getActDto().get(0).getEndTime(), goods.getActDto().get(0).getNow()));
				}
				ICategoryService ics = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_CATEGORY, ICategoryService.class);
				reqBo.setParam("goodsId", reqBo.getParam("id"));
				GoodsCategory gc = ics.selectCategoryNames(reqBo).getResult();
				view.addObject("firstName", gc.getFirstName());
				view.addObject("firstId", gc.getOneId());
				view.addObject("secondName", gc.getSecondName());
				view.addObject("secondId", gc.getTwoId());
			} else {
				view = new ModelAndView("index");
			}
		}
		//商品推荐
		ReqBo reqBo4TopN = new ReqBo();
		reqBo4TopN.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		ITopNService tns = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_TOPN, ITopNService.class);
		ResBo<List<Map<Object,Object>>> rb = tns.selectTopNGoodsByCityId(reqBo4TopN);
		List<Map<Object, Object>> topGoods = rb.getResult();
		
		view.addObject("topGoods", topGoods);
		return view;
	}
	
	/**
	 * 加入购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "addCart.htm")
	@ResponseBody
	public ResBo<ShopCart> addCart(HttpServletRequest request, HttpServletResponse response) {
		return putCart(request, response);
	}
	
	/**
	 * 立即购买
	 * @param request
	 * @param response
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "buyNow.htm")
	@ResponseBody
	public ResBo<ShopCart> buyNow(HttpServletRequest request, HttpServletResponse response) {
		 return putCart(request, response);
	}

	/**
	 * 公用的放入购物车方法
	 * @param request
	 * @return
	 */
	public ResBo<ShopCart> putCart(HttpServletRequest request, HttpServletResponse response) {
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		IShopCartService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		Cart cart = new Cart();
		cart.addCartGood(reqBo.getParamLong("_goods_id"), reqBo.getParamInt("_goods_num"));
		reqBo.setReqModel(cart);
		reqBo.setParam("provinceId", (Integer)SessionUtil.get(Constants.SESSION_PROVINCEID));
		reqBo.setParam("cityId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		if(user != null){
			reqBo.setParam("userId", user.getId());
			reqBo.setParam("isCommonUser", Constants.USER_TPYE_COMMON == user.getMemberType().intValue());
			reqBo.getParam("pavilionId", user.getPavilionId());
		}else{
			//cookie中的购物车信息
			Cookie cookie = CookieUtil.getCookie(request, Constants.COOKIE_CART);
			if(cookie != null){
				String cartCookie = cookie.getValue();
				reqBo.setParam(Constants.COOKIE_CART, cartCookie);
			}
		}
		ResBo<ShopCart> rbsc = is.insertShopCart(reqBo);
		if (rbsc.isSuccess()) {
			if (user == null) {
				CookieUtil.setCookie(request, response, Constants.COOKIE_CART, rbsc.getResult().getCart());//用户未登陆时，商品放到cookie中
			}
		}
		return rbsc;
	}
	
	/**
	 * 加入收藏
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addFavorite.htm")
	@ResponseBody
	public ResBo<MyFavorite> addFavorite(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IMyFavoriteService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_MYFAVORITE, IMyFavoriteService.class);
		MyFavorite mf = new MyFavorite();
		mf.setUserId(((User)SessionUtil.get(Constants.SESSION_USER_INFO)).getId());
		mf.setGoodsId(reqBo.getParamLong("_goods_id"));
		mf.setIsDel(1);
		mf.setCreateTime(new Date());
		reqBo.setReqModel(mf);
		ResBo<MyFavorite> rbmf = is.insertMyFavorite(reqBo);
		return rbmf;
	}
	
	/**
	 * 商品搜索结果页
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getGoodsListBySearch.htm")
	public ModelAndView getGoodsListBySearch(HttpServletRequest request) throws UnsupportedEncodingException {
		ReqBo reqBo = new ReqBo(request);
		ModelAndView view = new ModelAndView("goods/goods_list_search");
		
		//按城市
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		//按综合，新品，销量，价格
		if (reqBo.getParam("sortTp") == null) {//直接点击搜索框后面按钮时
			view.addObject("sortTp", "sort_zh");
		} else if (reqBo.getParam("sortTp") != null) {//点击综合或新品或销量或价格时
			if ("sort_new".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortNew", 0);
				view.addObject("sortTp", reqBo.getParam("sortTp"));
			} else if ("sort_sale".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortSale", 0);
				view.addObject("sortTp", reqBo.getParam("sortTp"));
			} else if ("sort_price_0".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortPrice", 0);
				view.addObject("sortTp", reqBo.getParam("sortTp"));
			} else if ("sort_price_1".equals(reqBo.getParam("sortTp"))) {
				reqBo.setParam("sortPrice", 1);
				view.addObject("sortTp", reqBo.getParam("sortTp"));
			} else {//参数不合法时
				reqBo.setParam("sortNew", null);
				reqBo.setParam("sortSale", null);
				reqBo.setParam("sortPrice", null);
				view.addObject("sortTp", "sort_zh");
			}
		}
		
		//按关键字
		if (reqBo.getParam("content") != null && !"".equals(reqBo.getParam("content"))) {
			String content = Constants.parseUTF8((String)reqBo.getParam("content"));
			reqBo.setParam("content", content);
			view.addObject("content", content);
			//对content进行转义
			view.addObject("contentEscape", Constants.escape4Html(content));
		} else {
			view.addObject("content", "");
		}
		
		int num = Constants.GOODS_NUM_PAGE;
		reqBo.setParam("num", num);
		
		if (reqBo.getParam("currPage") == null || !reqBo.getParam("currPage").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("currPage", 1);
		}
		
		long total_num = 0l;
		long total_page = 0l;
		List<CommodityDto> l = new ArrayList<CommodityDto>();
		if (reqBo.getParam("content") != null && !"".equals(reqBo.getParam("content"))) {
			IGoodsService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_GOODS, IGoodsService.class);
			ResBo<Pager<List<CommodityDto>>> r = is.sreachGoods(reqBo);
			Pager<List<CommodityDto>> p =  r.getResult();
			l = p.getEntry();
			total_num = p.getTotal();
			total_page = (total_num + num - 1) / num;
		}
		
		//人气商品
		ReqBo reqBo4TopN = new ReqBo();
		reqBo4TopN.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		ITopNService tns = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_TOPN, ITopNService.class);
		ResBo<List<Map<Object,Object>>> rb = tns.selectTopNGoodsByCityId(reqBo4TopN);
		List<Map<Object, Object>> topGoods = rb.getResult();
		
		view.addObject("topGoods", topGoods);
		view.addObject("goodslistsearch", l);
		view.addObject("currPage", reqBo.getParam("currPage"));
		view.addObject("total_page", total_page);
		view.addObject("areaId", reqBo.getParam("areaId"));
		
		return view;
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
		} else {//一级分类，二级分类id数值不存在或不合法时
//			reqBo.setParam("first", Constants.SC_ID);//默认按第一个蔬菜类去查找全部的商品
//			reqBo.setParam("second", null);
//			view.addObject("first", reqBo.getParam("first"));
//			view.addObject("second", "");
//			
//			reqBo.setParam("currPage", 1);
//			
//			p = is.selectProductPageList(reqBo).getResult();
//			total_num = p.getTotal();
//			total_page = (total_num + num - 1) / num;	
//			l = p.getEntry();
			
			//前台页面直接显示“空列表消息提示框”
		}
		// 市场节日活动title
		IDynpageService ids= FindServiceUtil.findRemoteService(Constants.SERVICE_URL_Dynpage, IDynpageService.class);
		ResBo<Dynpage> resBoDynapge = ids.selectByPrimaryKey(reqBo);
		Dynpage dy = resBoDynapge.getResult();
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
		view.addObject("dynpageId", reqBo.getParamInt("dynpageId"));
		view.addObject("title", dy.getTitle());
		
		return view;
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
		view.addObject("titleType", reqBo.getParamInt("type"));
		return view;
		
	}
	
}
