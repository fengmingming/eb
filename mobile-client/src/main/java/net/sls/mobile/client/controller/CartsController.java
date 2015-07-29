package net.sls.mobile.client.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.mobile.act.Buyer;
import net.sls.dto.mobile.product.SettlementsDto;
import net.sls.dto.mobile.product.ShopCart;
import net.sls.dto.mobile.shopcart.Cart;
import net.sls.dto.mobile.shopcart.ProductDto;
import net.sls.dto.mobile.shopcart.ShopCartDto;
import net.sls.dto.mobile.user.User;
import net.sls.dto.mobile.user.UserAddress;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.controller.vo.UserAddressVo;
import net.sls.mobile.client.util.Constants;
import net.sls.mobile.client.util.CookieUtil;
import net.sls.service.mobile.product.IShopCartService;
import net.sls.service.mobile.user.IUserAddressService;
import net.sls.service.mobile.user.IUserService;

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
 * 购物车
 * @author huzeyu 2015-04-10
 *
 */
@Controller
@RequestMapping("carts")
public class CartsController {
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
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		IShopCartService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		Cart cart = new Cart();
		cart.addCartGood(reqBo.getParamLong("goodsId"), reqBo.getParamInt("goodsNum"));
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
	 * 获取购物车数量
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getShopCartCount.htm")
	@ResponseBody
	public ResBo<Integer> getShopCartCount(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		Object userId = SessionUtil.get(Constants.SESSION_USER_ID);
		if (userId != null) {
			reqBo.setParam("userId", userId);
		} else {
			//cookie中的购物车信息
			Cookie cookie = CookieUtil.getCookie(request, Constants.COOKIE_CART);
			if(cookie != null){
				String cartCookie = cookie.getValue();
				reqBo.setParam(Constants.COOKIE_CART, cartCookie);
			}
		}
		IShopCartService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		ResBo<Integer> r = is.getShopCartCount(reqBo);
		return r;
	}
	
	/**
	 * 我的购物车列表
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "myCarts.htm")
	public ModelAndView myCarts(HttpServletRequest request){
		ModelAndView view = new ModelAndView("carts/my_carts");
		ReqBo reqBo = setReqParam(request, true);
		IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		ResBo<SettlementsDto> settleShopCart = ss.settleShopCart(reqBo);
		if(settleShopCart.isSuccess() && settleShopCart.getResult() != null ){
			//只有购物车中已勾选的商品需要购物车列计算个数
			SettlementsDto settlementsDto = settleShopCart.getResult();
			int number = 0;
			for(ProductDto product : settlementsDto.getProductList()){
				if(product.isChecked()){
					number = number + product.getNumber();
				}
			}
			settlementsDto.setNumber(number);
			view.addObject("shopCart", settleShopCart.getResult());
		}
		//如果用户地址信息不全，会导致无法购买某些限购的商品，需要用户补全
		Buyer buyer = (Buyer)reqBo.getReqModel();
		if(buyer != null && buyer.isNeedMoreInfo()){
			view.addObject("needMoreInfo", "true");
		}
		return view;
	}
	
	/**
	 * 删除购物车中的商品
	 * @param request
	 * @param response
	 * @param ids
	 * @throws IOException
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "deleteProducts.htm")
	@ResponseBody
	public void deleteProducts(HttpServletRequest request,
			HttpServletResponse response, String ids) throws IOException{
		ReqBo reqBo = setReqParam(request, false);
		ShopCartDto shopCartDto = new ShopCartDto(ids);
		reqBo.setReqModel(shopCartDto);
		IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		ResBo<ShopCartDto> resBoReturn = ss.delete(reqBo);
		setResponse(request, response, resBoReturn);
	}

	/**
	 * 修改购物车中商品的数量
	 * @param request
	 * @param response
	 * @param id
	 * @param count
	 * @throws IOException
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "changeNumber.htm")
	@ResponseBody
	public void changeNumber(HttpServletRequest request,
			HttpServletResponse response, String id, Integer count) throws IOException{
		ReqBo reqBo = setReqParam(request, false);
		Cart cart = new Cart(id);
		cart.setNumber(count);
		reqBo.setReqModel(cart);
		IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		ResBo<ShopCartDto> resBoReturn = ss.changeNumberGoodsCartDto(reqBo);
		setResponse(request, response, resBoReturn);
	}
	
	/**
	 * 修改购物车中商品的状态，是否勾选
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "changeStatus.htm")
	@ResponseBody
	public void changeStatus(HttpServletRequest request,
			HttpServletResponse response, String ids, String checkeds) throws IOException{
		ReqBo reqBo = setReqParam(request, false);
		List<Cart> cartList = new ArrayList<Cart>();
		String[] idArrays = ids.split("&");
		String[] checkedArrays = checkeds.split("&");
		for(int index=0; index<idArrays.length; index++){
			Cart cart = new Cart(idArrays[index]);
			cart.setSelect(("Y".equalsIgnoreCase(checkedArrays[index]))?true:false);
			cartList.add(cart);
		}
		ShopCartDto shopCartDto = new ShopCartDto();
		shopCartDto.setCartList(cartList);
		reqBo.setReqModel(shopCartDto);
		IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		ResBo<ShopCartDto> resBoReturn = ss.changeState(reqBo);
		setResponse(request, response, resBoReturn);
	}
	
	/**
	 * 判断库存是否充足
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "isEnough.htm")
	@ResponseBody
	public void isEnough(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		ReqBo reqBo = setReqParam(request, true);
		IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		ResBo<SettlementsDto> settlements = ss.settleShopCart(reqBo);
		if(settlements != null && settlements.isSuccess() && 
				settlements.getResult() != null && settlements.getResult().isFlag()){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("fail");
		}
		response.setContentType("text/html;charset=UTF-8");
	}
	
	/**
	 * 购物车结算
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "amount.htm")
	public ModelAndView amount(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("order/check_order");
		ReqBo reqBo = setReqParam(request, true);
		//地址信息
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", 3);
		IUserAddressService ius = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		ResBo<Pager<List<UserAddress>>> reqAddress = ius.selectUserAddressListsByUserId(reqBo);
		if(reqAddress.isSuccess() && reqAddress.getResult() != null){
			List<UserAddressVo> addressVos = new ArrayList<UserAddressVo>();
			for(UserAddress address : reqAddress.getResult().getEntry()){
				UserAddressVo userAddressVo = UserAddressVo.convertUserAddressToVo(address);
				if(address.getIsdefault()){
					view.addObject("default", userAddressVo);
					view.addObject("defaultId", userAddressVo.getId());
				}
				addressVos.add(userAddressVo);
			}
			view.addObject("addresses", addressVos);
		}
		
		//购物车信息
		IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		ResBo<SettlementsDto> settleShopCart = ss.settleShopCart(reqBo);
		if(settleShopCart.isSuccess() && settleShopCart.getResult() != null ){
			//只有购物车中已勾选的商品需要在订单页显示
			SettlementsDto settlementsDto = settleShopCart.getResult();
			List<ProductDto> products = new ArrayList<ProductDto>();
			int number = 0;
			for(ProductDto product : settlementsDto.getProductList()){
				if(product.isChecked()){
					number = number + product.getNumber();
					products.add(product);
				}
			}
			settlementsDto.setProductList(products);
			settlementsDto.setNumber(number);
			view.addObject("shopCart", settleShopCart.getResult());
		}
		
		//余额信息
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER,IUserService.class);
		ResBo<User> user = us.getUserByUserId(reqBo);
		if(settleShopCart.isSuccess() && settleShopCart.getResult() != null){
			BigDecimal amount = settleShopCart.getResult().getAmount();
			if(amount != null && user.getResult().getAmount() != null &&
					amount.doubleValue() > user.getResult().getAmount().doubleValue()){
				view.addObject("balanceMsg", "余额不足");
			}
		}
		//未设置支付密码
		if(user.isSuccess() && user.getResult() != null){
			String payPassword = user.getResult().getPayPassword();
			if(payPassword == null || payPassword.trim().equals("")){
				view.addObject("payPasswordHint", "您未设置支付密码，请先设置支付密码。");
			}
		}
		return view;
	}
	
	/**
	 * 设置请求的参数信息
	 * @param request
	 * @param needAddress 是否需要地址信息
	 * @return
	 */
	private ReqBo setReqParam(HttpServletRequest request, boolean needAddress) {
		ReqBo reqBo = new ReqBo(request);
		//session中的用户信息
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		if(user != null){
			reqBo.setParam("userId", user.getId());
			//用户地址信息（使用注册信息中的地址信息）
			if(needAddress){
				Buyer buyer = new Buyer();
				buyer.setUserId(user.getId());
				buyer.setProvinceId(user.getProvince());
				buyer.setCityId(user.getCity());
				buyer.setDistrictId(user.getDistrict());
				buyer.setCommunityId(user.getCommunity());
				buyer.setPavilionId(user.getPavilionId());
				buyer.setIsCommonUser(Boolean.TRUE);
				reqBo.setReqModel(buyer);
			}
		}else{
			//cookie中的购物车信息
			Cookie cookie = CookieUtil.getCookie(request, Constants.COOKIE_CART);
			if(cookie != null){
				String cartCookie = cookie.getValue();
				reqBo.setParam(Constants.COOKIE_CART, cartCookie);
			}
		}
		return reqBo;
	}
	
	/**
	 * 修改cookie信息（如果当前用户未登录，需要修改cookie）
	 * @param request
	 * @param response
	 * @param resBoReturn
	 */
	private void modifyCookie(HttpServletRequest request,
			HttpServletResponse response, ResBo<ShopCartDto> resBoReturn) {
		if(SessionUtil.get(Constants.SESSION_USER_ID) == null){
			CookieUtil.setCookie(request, response, Constants.COOKIE_CART, 
					resBoReturn.getResult().toString());
		}
	}
	
	/**
	 * 设置响应信息
	 * @param request
	 * @param response
	 * @param resBoReturn
	 * @throws IOException
	 */
	private void setResponse(HttpServletRequest request,
			HttpServletResponse response, ResBo<ShopCartDto> resBoReturn)
			throws IOException {
		if(resBoReturn != null && resBoReturn.isSuccess()){
			response.getWriter().print("success");
			modifyCookie(request, response, resBoReturn);
		}else{
			response.getWriter().print("fail");
		}
		response.setContentType("text/html;charset=UTF-8");
	}
}
