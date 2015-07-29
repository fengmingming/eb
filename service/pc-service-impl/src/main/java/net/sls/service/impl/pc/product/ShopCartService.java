package net.sls.service.impl.pc.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sls.commons.businessconstant.BusinessContant;
import net.sls.component.pc.act.IActivityGoodsComponent;
import net.sls.component.pc.act.ICouponComponent;
import net.sls.component.pc.act.IHandler;
import net.sls.component.pc.act.ISettleComponent;
import net.sls.component.pc.act.impl.EndHandler;
import net.sls.component.pc.product.IAreaComponent;
import net.sls.component.pc.product.IGoodsAreaComponent;
import net.sls.component.pc.product.IGoodsComponent;
import net.sls.component.pc.product.IGoodsStockComponent;
import net.sls.component.pc.product.IShopCartComponent;
import net.sls.component.pc.user.IUserComponent;
import net.sls.dto.pc.act.ActivityGoods;
import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.product.GoodsArea;
import net.sls.dto.pc.product.GoodsStock;
import net.sls.dto.pc.product.SettlementsDto;
import net.sls.dto.pc.product.ShopCart;
import net.sls.dto.pc.shopcart.Cart;
import net.sls.dto.pc.shopcart.CartGood;
import net.sls.dto.pc.shopcart.GoodDto;
import net.sls.dto.pc.shopcart.SSettleGood;
import net.sls.dto.pc.shopcart.ShopCartDto;
import net.sls.service.pc.product.IShopCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.Area;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service("shopCartService")
public class ShopCartService implements IShopCartService{
	
	@Autowired
	private IShopCartComponent shopCartComponent;
	@Autowired
	private IGoodsComponent goodsComponent;
	@Autowired
	private IGoodsAreaComponent goodsAreaComponent;
	@Autowired
	private IGoodsStockComponent goodsStockComponent;
	@Autowired
	private ISettleComponent settleComponent;
	@Autowired
	private IActivityGoodsComponent ActivityGoodsComponent;
	@Autowired
	private IAreaComponent areaComponent;
	@Autowired
	private IUserComponent userComponent;
	@Autowired
	private ICouponComponent couponComponent;
	
	@Override
	public ResBo<ShopCart> insertShopCart(ReqBo reqBo) {
		Long userId=reqBo.getParamLong("userId");
		ShopCart shopCart = new ShopCart();
		shopCart.setUserId(userId);
		ShopCartDto shopCartDto = null;
		if (null == userId) {
			String cartStr= reqBo.getParamStr(BusinessContant.COOKIE_CART);
			shopCartDto = new ShopCartDto(cartStr);
		}else{
			shopCart = shopCartComponent.selectShopCart(userId);
			if(shopCart == null){
				shopCart = new ShopCart();
				shopCart.setUserId(userId);
				shopCartComponent.insertShopCart(shopCart);
				shopCartDto = new ShopCartDto(null);
			}else{
				shopCartDto = new ShopCartDto(shopCart.getCart());
			}
		}
		if (shopCartDto.getCartList().size() >= BusinessContant.SHOPCART_MAX) {
			return new ResBo<ShopCart>(11L);
		}
		Integer provinceId = reqBo.getParamInt("provinceId");
		Integer cityId = reqBo.getParamInt("cityId");
		Integer districtId = reqBo.getParamInt("districtId");
		Integer communityId = reqBo.getParamInt("communityId");
		Integer pavilionId = reqBo.getParamInt("pavilionId");
		Cart cart = reqBo.getReqModel(Cart.class);
		List<CartGood> goodIdList = cart.getCartGoods();
		GoodDto goodDto = null;
		GoodsArea goodsArea = null;
		GoodsStock goodsStock=new GoodsStock();
		for (CartGood cartGood : goodIdList) {
			int number = 0;
			// 查 goods表
			goodDto = goodsComponent.selectGoodDto(cartGood.getId());
			goodsStock = goodsStockComponent.selectGoodsStock(cartGood.getId());
			if(goodDto != null){
				//0不限制1省2市3区4商圈5亭子
				switch (goodDto.getLimittype()) {
				case 1:
					if(provinceId != null){
						goodsArea=goodsAreaComponent.selectGoodsAreaByFilter(cartGood.getId(),provinceId,null,null,null,null);
					}
					break;
				case 2:
					if(!(provinceId == null||cityId == null)){
						goodsArea=goodsAreaComponent.selectGoodsAreaByFilter(cartGood.getId(),provinceId,cityId,null,null,null);
					}
					break;
				case 3:
					if(!(provinceId == null||cityId == null||districtId == null)){
						goodsArea=goodsAreaComponent.selectGoodsAreaByFilter(cartGood.getId(),provinceId,cityId,districtId,null,null);
					}
					break;
				case 4:
					if(!(provinceId == null||cityId == null||districtId == null||communityId == null)){
						goodsArea=goodsAreaComponent.selectGoodsAreaByFilter(cartGood.getId(),provinceId,cityId,districtId,communityId,null);
					}
					break;
				case 5:
					if(!(provinceId == null||cityId == null||districtId == null||communityId == null||pavilionId == null)){
						goodsArea=goodsAreaComponent.selectGoodsAreaByFilter(cartGood.getId(),provinceId,cityId,districtId,communityId,pavilionId);
					}
					break;
				default:break;
				}
				if (goodsArea != null && goodsArea.getIsLimit()) {
					number = Math.min(goodsArea.getNumber(), goodsStock.getVirtualNumber());
				}else{
					number = goodsStock.getVirtualNumber();
				}
				if(number < cart.getNumber()*cartGood.getNumber()){
					return new ResBo<ShopCart>(10L,goodDto.getGoodsName());
				}
				//活动限购
				if(cityId != null && number > 0){
					List<Long> goodsIds = new ArrayList<Long>();
					goodsIds.add(cartGood.getId());
					List<String> areaCodeList = new ArrayList<String>();
					Area area = areaComponent.selectAreaById(cityId);
					if(area != null){
						//市id
						areaCodeList.add(area.getCode());
						//省id
						area = areaComponent.selectAreaById(area.getPid());
						if(area != null){
							areaCodeList.add(area.getCode());
						}
					}
					areaCodeList.add("1");
					if(area != null && area.getCode() != null){
						List<ActivityGoods> ags = ActivityGoodsComponent.selectUserActivityGoods(userId, 
								reqBo.getParamBoolean("isCommonUser"), null, goodsIds, areaCodeList);
						if(ags != null && ags.size() > 0){
							number = Math.min(number, ags.get(0).getCanBuyNumber());
							if(number < cart.getNumber()*cartGood.getNumber()){
								return new ResBo<ShopCart>(47L, goodDto.getGoodsName(), number);
							}
						}
					}
				}
			}else{
				return new ResBo<ShopCart>(23L);
			}
		}
		List<Cart> cartList=new ArrayList<Cart>();
		cartList.add(cart);
		shopCartDto = shopCartDto.addCart(cartList);
		shopCart.setCart(shopCartDto.toString());
		if(userId != null){
			shopCartComponent.updateShopCart(shopCart);
		}
		return new ResBo<ShopCart>(shopCart);
	}

	@Override
	public ResBo<ShopCartDto> merge(ReqBo reqBo) {
		ResBo<ShopCartDto> resBo = new ResBo<ShopCartDto>();
		Long userId=reqBo.getParamLong("userId");
		String cartStr=reqBo.getParamStr(BusinessContant.COOKIE_CART);
		ShopCartDto cookiesShopCart = new ShopCartDto(cartStr);
		ShopCart shopCartT=shopCartComponent.selectShopCart(userId);
		//如果 购物车表没有记录
		if (shopCartT==null) {
			ShopCart shopCart=new ShopCart();
			shopCart.setUserId(userId);
			shopCart.setCart(cookiesShopCart.toString());
			shopCartComponent.insertShopCart(shopCart);
			resBo.setResult(cookiesShopCart);
		}else{
			ShopCartDto shopCartDto=new ShopCartDto(shopCartT.getCart());
			cookiesShopCart.mergeCart(shopCartDto);
			shopCartT.setCart(cookiesShopCart.toString());
			shopCartComponent.updateShopCart(shopCartT);
			resBo.setResult(cookiesShopCart);
		}
		return resBo;
	}

	@Override
	public ResBo<ShopCartDto> delete(ReqBo reqBo) {
		ResBo<ShopCartDto> resBo = new ResBo<ShopCartDto>();
		Long userId=reqBo.getParamLong("userId");
		ShopCartDto shopCartDto = reqBo.getReqModel(ShopCartDto.class);
		if (null==userId) {
			String cartList=reqBo.getParamStr(BusinessContant.COOKIE_CART);
			ShopCartDto cookieShopCartDto = new ShopCartDto(cartList);
			resBo.setResult(cookieShopCartDto.deleteCart(shopCartDto.getCartList()));
		}else{
			ShopCart shopCartT=shopCartComponent.selectShopCart(userId);
			ShopCartDto dbShopCartDto = new ShopCartDto(shopCartT.getCart());
			dbShopCartDto.deleteCart(shopCartDto.getCartList());
			shopCartT.setCart(dbShopCartDto.toString());
			shopCartComponent.updateShopCart(shopCartT);
			resBo.setResult(dbShopCartDto);
		}
		return resBo;
	}

	@Override
	public ResBo<ShopCartDto> selectShopCart(ReqBo reqBo) {
		ResBo<ShopCartDto> resBo = new ResBo<ShopCartDto>();
		ShopCart shopCart = shopCartComponent.selectShopCart(reqBo.getParamLong("userId"));
		if(shopCart != null){
			resBo.setResult(new ShopCartDto(shopCart.getCart()));
		}
		return resBo;
	}

	/**
	 * 得到结算对象，库存足的话有总额和总数，库存不足总额和总数没用（在这里没有给它们封装）
	 * 
	 */
	@Override
	public ResBo<SettlementsDto> settleShopCart(ReqBo reqBo) {
		Buyer buyer = reqBo.getReqModel(Buyer.class);
		ShopCartDto shopCartDto = null;
		if(buyer != null&&buyer.isLogin()){
			buyer.commitOrderVerfiy();
			ShopCart shopCart = shopCartComponent.selectShopCart(buyer.getCreateUserId());
			String cartStr = shopCart == null?"":shopCart.getCart();
			shopCartDto = new ShopCartDto(cartStr);
		}else{
			shopCartDto = new ShopCartDto(reqBo.getParamStr(BusinessContant.COOKIE_CART));
		}
		List<Cart> carts = shopCartDto.getCartList();
		if(carts.size() == 0){
			return new ResBo<SettlementsDto>();
		}
		List<Long> ids = new ArrayList<Long>();
		List<SSettleGood> ssgs = settleComponent.selectSettleGood(carts,buyer,ids);
		List<IHandler> handlers = settleComponent.selectActHandler(carts,buyer,ids);
		handlers.add(new EndHandler());
		Collections.sort(handlers,new Comparator<IHandler>(){
			@Override
			public int compare(IHandler i1, IHandler i2) {
				if(i1.getActType() > i2.getActType()){
					return 1;
				}else if(i1.getActType() < i2.getActType()){
					return -1;
				}
				return 0;
			}
		});
		SettlementsDto settlementDto = new SettlementsDto();
		if(buyer != null && buyer.isLogin() && buyer.getUserCouponId() != null){
			settlementDto.setUserCouponId(buyer.getUserCouponId());
		}
		for(IHandler handler:handlers){
			settlementDto.getProductList().addAll(handler.handler(buyer,ids,ssgs));
		}
		settlementDto.build();
		if(buyer != null && buyer.isLogin()){
			String cartStr = settlementDto.toString();
			if(!shopCartDto.toString().equals(cartStr)){
				ShopCart sc = new ShopCart();
				sc.setCart(cartStr);
				sc.setUserId(buyer.getCreateUserId());
				shopCartComponent.updateShopCart(sc);
			}
		}
		return new ResBo<SettlementsDto>(settlementDto);
	}
	
	@Override
	public ResBo<ShopCartDto> changeNumberGoodsCartDto(ReqBo reqBo) {
		ResBo<ShopCartDto> resBo = new ResBo<ShopCartDto>();
		Long userId=reqBo.getParamLong("userId");
		Cart cart=reqBo.getReqModel(Cart.class);
		ShopCartDto scd = null;
		ShopCart sc = null;
		if (null==userId) {
			String cartStr=reqBo.getParamStr(BusinessContant.COOKIE_CART);
			scd = new ShopCartDto(cartStr);
		}else{
			sc = shopCartComponent.selectShopCart(userId);
			scd = new ShopCartDto(sc.getCart());
			
		}
		scd.modifyNumber(cart);
		if(userId != null){
			sc.setCart(scd.toString());
			shopCartComponent.updateShopCart(sc);
		}
		resBo.setResult(scd);
		return resBo;
	}

	@Override
	public ResBo<ShopCartDto> changeState(ReqBo reqBo) {
		ResBo<ShopCartDto> resBo = new ResBo<ShopCartDto>();
		Long userId=reqBo.getParamLong("userId");
		ShopCartDto delShopCartDto = reqBo.getReqModel(ShopCartDto.class);
		if (null==userId) {
			String cartStr = reqBo.getParamStr(BusinessContant.COOKIE_CART);
			ShopCartDto shopCartDto = new ShopCartDto(cartStr);
			resBo.setResult(shopCartDto.changeSelect(delShopCartDto.getCartList()));
		}else{
			ShopCart  sc = shopCartComponent.selectShopCart(userId);
			ShopCartDto shopCartDto = new ShopCartDto(sc.getCart());
			shopCartDto.changeSelect(delShopCartDto.getCartList());
			sc.setCart(shopCartDto.toString());
			shopCartComponent.updateShopCart(sc);
			resBo.setResult(shopCartDto);
		}
		return resBo;
	}

	@Override
	public ResBo<Integer> getShopCartCount(ReqBo reqBo) {
		Long userId = reqBo.getParamLong("userId");
		ShopCartDto dto = null;
		if(userId == null){
			String cartStr = reqBo.getParamStr(BusinessContant.COOKIE_CART);
			dto = new ShopCartDto(cartStr);
		}else{
			ShopCart  sc = shopCartComponent.selectShopCart(userId);
			if(sc == null){
				return new ResBo<Integer>((Integer)0);
			}
			dto = new ShopCartDto(sc.getCart());
		}
		int count = 0;
		for(Cart cart : dto.getCartList()){
			count = count + cart.getNumber();
		}
		return new ResBo<Integer>(new Integer(count));
	}
}
