package net.sls.pc.client.controller.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.product.SettlementsDto;
import net.sls.dto.pc.shopcart.Cart;
import net.sls.dto.pc.shopcart.ProductDto;
import net.sls.dto.pc.shopcart.ShopCartDto;
import net.sls.dto.pc.user.User;
import net.sls.dto.pc.user.UserAddress;
import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.controller.vo.UserAddressVo;
import net.sls.pc.client.util.Constants;
import net.sls.pc.client.util.CookieUtil;
import net.sls.service.pc.product.IPavilionInfoService;
import net.sls.service.pc.product.IShopCartService;
import net.sls.service.pc.user.IUserAddressService;
import net.sls.service.pc.user.IUserService;

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
 * @author huzeyu 2014-12-30
 *
 */
@Controller
@RequestMapping("carts")
public class CartsController {
	/**
	 * 我的购物车列表
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "myCarts.htm")
	public ModelAndView myCarts(HttpServletRequest request){
		ModelAndView view = new ModelAndView("carts/mycarts");
		SessionUtil.delete(Constants.SESSION_ORDER_OWNER); //清空被代购用户的缓存
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
		return view;
	}
	
	/**
	 * 删除购物车中的商品，单个删除和批量删除
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
//		Buyer buyer = (Buyer)reqBo.getReqModel();
//		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
//		User orderOwner = (User)SessionUtil.get(Constants.SESSION_ORDER_OWNER);
//		if(Constants.USER_TPYE_COMMON == user.getMemberType().intValue()){
//			buyer.setIsCommonUser(Boolean.TRUE);
//		}
//		if(Constants.USER_TPYE_PAVILION == user.getMemberType().intValue() &&
//				orderOwner != null){
//			buyer.setUserId(orderOwner.getId());
//			buyer.setIsCommonUser(Constants.USER_TPYE_COMMON == orderOwner.getMemberType().intValue());
//		}
		IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
		ResBo<SettlementsDto> settlements = ss.settleShopCart(reqBo);
		SettlementsDto settlementsDto = settlements.getResult();
		if (settlements != null && settlements.isSuccess() && 
				settlementsDto != null && settlementsDto.isFlag()) {
			response.getWriter().print("success");
            int number = 0;
            for (ProductDto product : settlementsDto.getProductList()) {
                if (product.isChecked()) {
                    number++;
                }
            }
            if(number == 0){
            	SessionUtil.delete(Constants.SESSION_ORDER_OWNER);
            	response.getWriter().print("fail");
            }
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
	public ModelAndView amount(HttpServletRequest request){
		ModelAndView view = null;
		ReqBo reqBo = setReqParam(request, true);
		if(((User)SessionUtil.get(Constants.SESSION_USER_INFO)).getMemberType().intValue() == 1){
			view = new ModelAndView("order/check_order"); //我的订单
			//地址信息
			reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
			reqBo.setParam("page", 1);
			reqBo.setParam("rows", Constants.ADDRESS_MAX_COUNT);
			IUserAddressService ius = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
			ResBo<Pager<List<UserAddress>>> reqAddress = ius.selectUserAddressListsByUserId(reqBo);
			if(reqAddress.isSuccess() && reqAddress.getResult() != null){
				for(UserAddress address : reqAddress.getResult().getEntry()){
					if(address.getIsdefault()){
						view.addObject("dftId", address.getId());
					}
					address.setAddressDetail(address.getAddressDetail().replaceAll("\n", " "));
				}
				view.addObject("addresses", reqAddress.getResult().getEntry());
			}
		}else{
			view = new ModelAndView("order/check_dgOrder"); //代购订单
		}

        view.addObject("isCardPay",false);
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
		
		//余额信息,返回余额是否可够支付
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
	 * 购物车结算刷卡(代购专用)
     * 在验证卡号或者是手机号时将取得的用户缓存在session中
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "amtByCard.htm")
	public ModelAndView amountByCard(HttpServletRequest request){
		ModelAndView view = null;
		int isCardPay = Integer.parseInt(request.getParameter("cardPay"));
        ReqBo reqBo = setReqParam(request, true);
        //从session中取得刷卡的用户信息,此信息在点击刷卡结算时通过getUserByCardNum设置
        User buyer = (User) SessionUtil.get(Constants.SESSION_ORDER_OWNER);
        if (buyer == null) {
            throw new RuntimeException("未能取得刷卡用户信息，请返回重试。");
        }
        view = new ModelAndView("order/check_dgOrder"); //代购订单
        view.addObject("buyerName",buyer.getUserName());
        view.addObject("buyerMobi",buyer.getMobile());
        if(isCardPay==1)    // cardPay ,此值用以标识从哪个账户捐款
            view.addObject("isCardPay",true);
        else                // otherwise
            view.addObject("isCardPay",false);

        reqBo.setParam("userId", buyer.getId());
        reqBo.setParam("page", 1);
        reqBo.setParam("rows", 3);
        IUserAddressService ius = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
        ResBo<Pager<List<UserAddress>>> reqAddress = ius.selectUserAddressListsByUserId(reqBo);
        if (reqAddress.isSuccess() && reqAddress.getResult() != null) {
            List<UserAddressVo> addressVos = new ArrayList<UserAddressVo>();
            for (UserAddress address : reqAddress.getResult().getEntry()) {
                UserAddressVo userAddressVo = convertUserAddressToVo(request, address);
                addressVos.add(userAddressVo);
            }
            view.addObject("addresses", addressVos);


            //购物车信息
            IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
            ResBo<SettlementsDto> settleShopCart = ss.settleShopCart(reqBo);
            if (settleShopCart.isSuccess() && settleShopCart.getResult() != null) {
                //只有购物车中已勾选的商品需要在订单页显示
                SettlementsDto settlementsDto = settleShopCart.getResult();
                List<ProductDto> products = new ArrayList<ProductDto>();
                int number = 0;
                for (ProductDto product : settlementsDto.getProductList()) {
                    if (product.isChecked()) {
                        number = number + product.getNumber();
                        products.add(product);
                    }
                }
                settlementsDto.setProductList(products);
                settlementsDto.setNumber(number);
                view.addObject("shopCart", settleShopCart.getResult());
            }

            //余额信息
            IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
            if(isCardPay != 1){  // cardPay ,此值用以标识从哪个账户扣款，非刷卡时验证亭子用户的余额
                reqBo.setParam("userId", SessionUtil.get(Constants.SESSION_USER_ID));
            }
            ResBo<User> user = us.getUserByUserId(reqBo);
            if (settleShopCart.isSuccess() && settleShopCart.getResult() != null) {
                BigDecimal amount = settleShopCart.getResult().getAmount();
                if (amount != null && user.getResult().getAmount() != null &&
                        amount.doubleValue() > user.getResult().getAmount().doubleValue()) {
                    view.addObject("balanceMsg", "余额不足");
                }
            }
        }
        return view;
    }
	/**
	 * 获取购物车数量，并显示在头部气泡上
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
	 * 将地址信息转化为页面显示类
	 * @param request
	 * @param address
	 * @return
	 */
	private UserAddressVo convertUserAddressToVo(HttpServletRequest request, UserAddress address) {
		UserAddressVo addressVo = new UserAddressVo();
		addressVo.setId(address.getId());
		addressVo.setReceiver(address.getReceiver());
		addressVo.setMobile(address.getMobile());
        addressVo.setPhone(address.getPhone());
		addressVo.setAddressDetail(address.getAddressDetail());
		addressVo.setProvince(address.getProvince());
		addressVo.setCity(address.getCity());
		addressVo.setProvince(address.getProvince());
		addressVo.setDistrict(address.getDistrict());
		addressVo.setCommunity(address.getCommunity());
		addressVo.setPavilionId(address.getPavilionId());
		addressVo.setPavilionCode(address.getPavilionCode());
		//得到亭子的信息
		if(address.getPavilionId() != null && address.getPavilionCode() != null){
			ReqBo reqBo = new ReqBo(request);
			reqBo.setParam("id", address.getPavilionId());
			IPavilionInfoService ps = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_PAVILION, IPavilionInfoService.class);
			ResBo<Map<Object,Object>> pavilion = ps.getPavilionInfoById(reqBo);
			if(pavilion.isSuccess() && pavilion.getResult() != null){
				addressVo.setPavilionName((String)pavilion.getResult().get("name"));
				addressVo.setPavilionPhone((String)pavilion.getResult().get("mobile"));
			}
		}else{
			addressVo.setPavilionName("");
			addressVo.setPavilionPhone("");
		}
		return addressVo;
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
				buyer.setCreateUserId(user.getId());
				User orderOwner = (User)SessionUtil.get(Constants.SESSION_ORDER_OWNER);
				if(Constants.USER_TPYE_COMMON == user.getMemberType().intValue()){
					buyer.setIsCommonUser(Boolean.TRUE);
				}
				if(Constants.USER_TPYE_PAVILION == user.getMemberType().intValue() &&
						orderOwner != null){
					buyer.setUserId(orderOwner.getId());
					buyer.setIsCommonUser(Constants.USER_TPYE_COMMON == orderOwner.getMemberType().intValue());
				}
				buyer.setProvinceId(user.getProvince());
				buyer.setCityId(user.getCity());
				buyer.setDistrictId(user.getDistrict());
				buyer.setCommunityId(user.getCommunity());
				buyer.setPavilionId(user.getPavilionId());
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
