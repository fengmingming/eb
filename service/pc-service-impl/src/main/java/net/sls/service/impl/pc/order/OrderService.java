package net.sls.service.impl.pc.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.component.pc.act.ICouponComponent;
import net.sls.component.pc.order.IOrderComponent;
import net.sls.component.pc.order.IOrderDetailComponent;
import net.sls.component.pc.order.IOrderRefundComponent;
import net.sls.component.pc.product.IAreaComponent;
import net.sls.component.pc.product.IGoodsAreaComponent;
import net.sls.component.pc.product.IGoodsComponent;
import net.sls.component.pc.product.IGoodsStockComponent;
import net.sls.component.pc.product.IPavilionInfoComponent;
import net.sls.component.pc.product.IShopCartComponent;
import net.sls.component.pc.user.IUserAddressComponent;
import net.sls.component.pc.user.IUserComponent;
import net.sls.dto.pc.act.AbstractHandler;
import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.act.Coupon;
import net.sls.dto.pc.order.DeliveryAddress;
import net.sls.dto.pc.order.OrderActGoodsInfo;
import net.sls.dto.pc.order.OrderBeanDto;
import net.sls.dto.pc.order.OrderDetail;
import net.sls.dto.pc.order.OrderLog;
import net.sls.dto.pc.order.OrderPay;
import net.sls.dto.pc.order.OrderRefund;
import net.sls.dto.pc.order.Orders;
import net.sls.dto.pc.order.ResOrder;
import net.sls.dto.pc.product.OrderStockDto;
import net.sls.dto.pc.product.SettlementsDto;
import net.sls.dto.pc.product.ShopCart;
import net.sls.dto.pc.shopcart.ProductDto;
import net.sls.dto.pc.shopcart.SSettleGood;
import net.sls.dto.pc.shopcart.SettleGood;
import net.sls.dto.pc.user.User;
import net.sls.service.pc.order.IOrderService;
import net.sls.service.pc.product.IShopCartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.AmountTypeEnum;
import util.model.Area;
import util.model.IsDeliveryEnum;
import util.model.IsPaidEnum;
import util.model.MemberTypeEnum;
import util.model.OrderStatusEnum;
import util.model.PayEnum;
import util.model.ShopTypeEnum;
import framework.exception.BusinessException;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service("orderService")
public class OrderService implements IOrderService{
	
	private Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private IOrderComponent orderComponent;
	
	@Autowired
	private IOrderDetailComponent orderDetailComponent;
	
	@Autowired
	private IGoodsComponent goodsComponent;
	
	@Autowired
	private IGoodsStockComponent goodsStockComponent;
	
	@Autowired
	private IGoodsAreaComponent goodsAreaComponent;
	
	@Autowired
	private IUserAddressComponent userAddressComponent;
	
	@Autowired
	private IShopCartService shopCartService;
	
	@Autowired
	private IAreaComponent areaComponent;
	
	@Autowired
	private IPavilionInfoComponent pavilionInfoComponent;
	
	@Autowired
	private IUserComponent userComponent;
	
	@Autowired
	private IShopCartComponent shopCartComponent;
	
	@Autowired
	private ICouponComponent couponComponent;
	
	@Autowired
	private IOrderRefundComponent orderRefundComponent;
	
	@Override
	public ResBo<Pager<List<Map<Object, Object>>>> selectOrderListsByUserId(
			ReqBo reqBo) {
		ResBo<Pager<List<Map<Object, Object>>>> resBo=new ResBo<Pager<List<Map<Object, Object>>>>();
		@SuppressWarnings("unchecked")
		List<Integer> statuslist=reqBo.getParamT("status", List.class);
		Pager<List<Map<Object, Object>>> pager=orderComponent.selectOrderListsByUserId(reqBo.getParamInt("state"),reqBo.getParamStr("sort"),reqBo.getParamStr("column"),reqBo.getParamLong("userId"),reqBo.getParamStr("orderNum"),reqBo.getParamInt("timeType"),statuslist,reqBo.getParamInt("isPaid"),reqBo.getParamInt("page"),reqBo.getParamInt("rows"));
		List<Map<Object, Object>> list=new ArrayList<Map<Object, Object>>();
		list=pager.getEntry();
		for (Map<Object, Object> map : list) {
			List<Map<Object,Object>> orderDetailList=orderDetailComponent.selectOrderDetailList(Long.valueOf(map.get("id").toString()));
			map.put("orderDetailList", orderDetailList);
			int count= orderDetailList.size();
			map.put("count", count);
		}
		resBo.setResult(pager);
		return resBo;
	}
	
	/**
	 * @param reqBo 
	 * @return ResBo<SettlementsDto>
	 * @date 2015年1月6日 下午4:29:45
	 */
	@Override
	public ResBo<ResOrder> insertOrderCommit(ReqBo reqBo) {
		Buyer buyer = reqBo.getReqModel(Buyer.class);
		Integer isCardPay = buyer.getIsCardPay();  // 是否为刷卡支付
		buyer.commitOrderVerfiy();
		buyer.addressVerify();
		User createOrderUser = userComponent.selectUserById(buyer.getCreateUserId());
        User payOrderUser = createOrderUser;    // 支付用户默认为创建订单用户(用户下单或者亭子下单并支付)
        if (isCardPay == null){                 // 没有是否卡付的标志时默认为不是卡付
            isCardPay = 0;
        }
        if(isCardPay == 1){                     // 如果刷卡支付，则取支付人的信息
            payOrderUser = userComponent.selectUserById(buyer.getUserId());
        }
		if(createOrderUser == null){
			return new ResBo<ResOrder>(27L);
		}
		if(buyer.getUserCouponId() != null){
			if(!couponComponent.selectCanUseCoupon(buyer.getUserCouponId(), buyer.getUserId())){
				return new ResBo<ResOrder>(51L);
			}
		}
		ResBo<SettlementsDto> rsSettle = shopCartService.settleShopCart(reqBo);
		SettlementsDto settle = rsSettle.getResult();
		
		if(settle == null||settle.getProductList().size() == 0||!settle.isFlag()){
			return new ResBo<ResOrder>(26L);
		}
		
		BigDecimal payPrice = settle.getPayPrice();
		BigDecimal orderPrice = BigDecimal.ZERO;
		BigDecimal useBalancePrice = BigDecimal.ZERO;
		BigDecimal discutPrice = settle.getDiscountPrice();
		
		orderPrice = payPrice;
		//使用余额的时候计算使用余额金额和应付金额
		if(buyer.isBalance()){
			if(payOrderUser.getPayPassword() == null||!payOrderUser.getPayPassword().equals(buyer.getPayPassword())){
				return new ResBo<ResOrder>(32L);
			}
			if(payOrderUser.getAccountType().equals(AmountTypeEnum.FREEZE.getCode())){
				return new ResBo<ResOrder>(34L);
			}
			if(buyer.getPayType() == null||buyer.getPayType().equals(PayEnum.Balance.getId())||buyer.getPayType().equals(PayEnum.Balance2.getId())){
				if(payOrderUser.getAmount().compareTo(settle.getPayPrice()) < 0){
					return new ResBo<ResOrder>(28L);
				}
				useBalancePrice = payPrice;
				payPrice = BigDecimal.ZERO;
			}else{
				if(payOrderUser.getAmount().compareTo(settle.getPayPrice()) < 0){
					useBalancePrice = payOrderUser.getAmount();
					payPrice = payPrice.subtract(payOrderUser.getAmount());
				}else{
					useBalancePrice = payPrice;
					payPrice = BigDecimal.ZERO;
				}
			}
		}
		Date cDate = new Date();
		OrderBeanDto orderBean = new OrderBeanDto();
		orderBean.setPayUsrId(payOrderUser.getId());
		//优惠券id，使用settle中的id，因为选择的优惠券不一定可以使用，buyer中只是选择的优惠券
		orderBean.setUserCouponId(settle.getUserCouponId());
		//判断总库存，组装减库存对象
		getStockAndAct(orderBean,settle);
		//设置下单用户
		orderBean.setCreateOrderUser(createOrderUser);
		//订单
		Orders order = new Orders();
		order.setIsCardBuy(isCardPay);// 设置是否卡付

		if(buyer.getUserId().longValue() == buyer.getCreateUserId().longValue() && 
				createOrderUser.getMemberType().intValue() == MemberTypeEnum.MemberType_1.getCode()){
			order.setShopType(ShopTypeEnum.ShopType_1.getCode()); //自购
			order.setOrderNum(generateOrderNum(ShopTypeEnum.ShopType_1));
		}else{
			order.setShopType(ShopTypeEnum.ShopType_2.getCode()); //代购
			order.setOrderNum(generateOrderNum(ShopTypeEnum.ShopType_2));
		}
		order.setStatus(OrderStatusEnum.status_2.getStatus());
		order.setPayPrice(payPrice);
		order.setOrderPrice(orderPrice);
		order.setAmount(settle.getAmount());
		order.setPaidPrice(useBalancePrice);
		order.setCreateTime(cDate);
		if(payPrice.compareTo(BigDecimal.ZERO) < 0){
			return new ResBo<ResOrder>(29L);
		}else if(payPrice.compareTo(BigDecimal.ZERO) == 0){
			order.setIsPaid(IsPaidEnum.IsPaid_2.getCode());
			order.setPayTime(cDate);
		}else{
			order.setIsPaid(IsPaidEnum.IsPaid_1.getCode());
		}
		String checkedCart = settle.toStringChecked();
		if(checkedCart.length() < 250){
			order.setCart(checkedCart);
		}
		order.setDiscutPrice(discutPrice);
		order.setUseBalancePrice(useBalancePrice);
		order.setDeliveryType(buyer.getDeliveryType());
		order.setUserId(buyer.getUserId());
		order.setCreateUserId(buyer.getCreateUserId());
		order.setChannelId(buyer.getChannelId());
		orderBean.setOrder(order);
		//地址
		orderBean.setAddress(converAddress(buyer));
		//支付
		OrderPay op = new OrderPay();
		op.setCreateTime(cDate);
		op.setUserId(buyer.getUserId());
		if(buyer.isBalance()&&payPrice.compareTo(BigDecimal.ZERO) <= 0){
			if(ShopTypeEnum.ShopType_1.getCode() == order.getShopType().intValue())
            {
				op.setPayCode(PayEnum.Balance.getId());
				op.setPayName(PayEnum.Balance.getName());
			}
            if(ShopTypeEnum.ShopType_2.getCode() == order.getShopType().intValue()) //代购
            {
                if(isCardPay == 1) //刷卡支付,扣用户的个人余额
                {
                    op.setPayCode(PayEnum.Balance.getId());
                    op.setPayName(PayEnum.Balance.getName());
                }
                else{ //代购，但不使用刷卡，使用亭子用户的余额
                    op.setPayCode(PayEnum.Balance2.getId());
                    op.setPayName(PayEnum.Balance2.getName());
                }
            }
		}
        else
        {
			op.setPayCode(PayEnum.getPayEnum(buyer.getPayType()).getId());
			op.setPayName(PayEnum.getPayEnum(buyer.getPayType()).getName());
		}
		orderBean.setOrderPay(op);
		//详情
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = null;
		for(ProductDto product:settle.getProductList()){
			if(product.isChecked()){
				for(SettleGood sg:product.getSettleGoods()){
					orderDetail = new OrderDetail();
					orderDetail.setCreateTime(cDate);
					orderDetail.setGoodsId(sg.getId());
					orderDetail.setMarketPrice(sg.getMarketPrice());
					orderDetail.setNumber(sg.getNumber()*product.getNumber());
					orderDetail.setPrice(sg.getPrice());
					orderDetail.setEffPay(sg.getEffPay());
					orderDetail.setProviderId(sg.getProviderId());
					orderDetail.setIsDelivery(IsDeliveryEnum.IsDelivery_0.getCode());
					orderDetails.add(orderDetail);
				}
			}
		}
		if(orderDetails.size() == 0){
			return new ResBo<ResOrder>(26L); 
		}
		orderBean.setDetails(orderDetails);
		orderComponent.insertOrder(orderBean);
		//日志
		OrderLog log = new OrderLog();
		log.setOperId(buyer.getCreateUserId());
		log.setOrderId(orderBean.getOrder().getId());
		log.setCreateTime(cDate);
		log.setRemark("下单成功");
		orderComponent.insertOrderLog(log);
		ShopCart sc = new ShopCart();
		sc.setCart(settle.toStringNotChecked());
		sc.setUserId(buyer.getCreateUserId());
		try{
			shopCartComponent.updateShopCart(sc);
		}catch(Exception e){
			logger.error("submit order success but update shopcart faild");
		}
		ResOrder resOrder = new ResOrder();
		resOrder.setOrderNum(orderBean.getOrder().getOrderNum());
		resOrder.setOrderId(orderBean.getOrder().getId());
		resOrder.setPayType(orderBean.getOrderPay().getPayCode());
		resOrder.setPayPrice(orderBean.getOrder().getPayPrice());
		resOrder.setCart(sc.getCart());
		return new ResBo<ResOrder>(resOrder);
	}
	
	private void getStockAndAct(OrderBeanDto dto,SettlementsDto settle){
		List<ProductDto> pds = settle.getProductList();
		Map<Long,Integer> stockMap = new HashMap<Long, Integer>();
		Map<Long,Integer> limitMap = new HashMap<Long, Integer>();
		Map<Long,Integer> maxMap = new HashMap<Long, Integer>();
		List<OrderActGoodsInfo> actGoodsList = new ArrayList<OrderActGoodsInfo>();
		SSettleGood ssg = null;
		for(ProductDto pd:pds){
			if(pd.isChecked()){
				for(SettleGood sg:pd.getSettleGoods()){
					ssg = (SSettleGood) sg;
					if(stockMap.get(ssg.getId()) == null){
						stockMap.put(ssg.getId(), ssg.getNumber()*ssg.get_number());
					}else{
						stockMap.put(ssg.getId(), stockMap.get(ssg.getId()) + (ssg.getNumber()*ssg.get_number()));
					}
					if(ssg.get_goodAreaId() != null&&ssg.isLimit()){
						if(limitMap.get(ssg.get_goodAreaId()) == null){
							limitMap.put(ssg.get_goodAreaId(), ssg.getNumber()*ssg.get_number());
						}else{
							limitMap.put(ssg.get_goodAreaId(), limitMap.get(ssg.get_goodAreaId()) + (ssg.getNumber()*ssg.get_number()));
						}
					}
					if(maxMap.get(ssg.getId()) == null){
						maxMap.put(ssg.getId(), ssg.getMinNumber());
					}
				}
				if(pd.getActHandlers() != null && pd.getActHandlers().size() > 0){
					for(AbstractHandler handler : pd.getActHandlers()){
						OrderActGoodsInfo oag = new OrderActGoodsInfo();
						oag.setActId(handler.getActId());
						oag.setGoodsId(pd.getSettleGoods().get(0).getId());
						oag.setActType(handler.getActType());
						oag.setNumber(pd.getNumber());
						actGoodsList.add(oag);
					}
					dto.setActGoodsList(actGoodsList);
				}
			}
		}
		for(Long l:maxMap.keySet()){
			if(maxMap.get(l).compareTo(stockMap.get(l)) < 0){
				throw new BusinessException(26L);
			}
		}
		List<OrderStockDto> stockList = new ArrayList<OrderStockDto>();
		List<OrderStockDto> limitList = new ArrayList<OrderStockDto>();
		for(Long l:stockMap.keySet()){
			stockList.add(new OrderStockDto(l, stockMap.get(l)));
		}
		for(Long l:limitMap.keySet()){
			limitList.add(new OrderStockDto(l, limitMap.get(l)));
		}
		dto.setStockList(stockList);
		dto.setLimitList(limitList);
	}

	private DeliveryAddress converAddress(Buyer buyer) {
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setProvince(buyer.getProvinceId());
		deliveryAddress.setCity(buyer.getCityId());
		deliveryAddress.setDistrict(buyer.getDistrictId());
		deliveryAddress.setCommunity(buyer.getCommunityId());
		deliveryAddress.setProvinceName(areaComponent.selectAreaNameById(buyer.getProvinceId()));
		deliveryAddress.setCityName(areaComponent.selectAreaNameById(buyer.getCityId()));
		deliveryAddress.setDistrictName(areaComponent.selectAreaNameById(buyer.getDistrictId()));
		deliveryAddress.setCommunityName(areaComponent.selectAreaNameById(buyer.getCommunityId()));
		deliveryAddress.setMobile(buyer.getMobile());
		deliveryAddress.setPhone(buyer.getPhone());
		deliveryAddress.setReceiver(buyer.getReceiver());
		deliveryAddress.setPostcode(buyer.getPostCode());
		if(buyer.getPavilionId() != null){
			deliveryAddress.setPavilionId(buyer.getPavilionId());
			Area area = areaComponent.selectAreaById(buyer.getPavilionId());
			if(area == null){
				throw new BusinessException(30L);
			}
			deliveryAddress.setPavilionCode(area.getCode());
			deliveryAddress.setPavilionName(areaComponent.selectAreaNameById(buyer.getPavilionId()));
		}else{
			//如果没有亭子信息，则保存为区/县的code，后台需要使用
			Area area = areaComponent.selectAreaById(buyer.getDistrictId());
			if(area != null){
				deliveryAddress.setPavilionCode(area.getCode());
			}
		}
		deliveryAddress.setRemark(buyer.getRemark());
		deliveryAddress.setStartdate(buyer.getStartDate());
		deliveryAddress.setEnddate(buyer.getEndDate());
		return deliveryAddress;
	}
	
	/**
	 * @Description: 生成定单号
	 * @date 2015年1月8日 下午3:16:02
	 */
	private String generateOrderNum(ShopTypeEnum shopTypeEnum) {
		//生成规则：DG/ZG(2)+年月日(12)+随机数(8),共22位
		StringBuilder orderNum = new StringBuilder();
		orderNum.append(shopTypeEnum.getPrefix());
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		orderNum.append(format.format(new Date()));
		//随机数(8位)   参数10的8次方
		String random = getRandom((int)Math.pow(10, 7));
		orderNum.append(random);
		return orderNum.toString();
	}
	
	private String getRandom(int i){
		 int l = String.valueOf(i).length();
	     int n =(int)((Math.random()*10)*i);
	     int k=l-String.valueOf(n).length(); 
	     String v="";
	     for(;k>0;k--){
	      v = v + "0";
	     }
	     return  v+String.valueOf(n);
	}

	@Override
	public ResBo<Pager<List<Map<Object, Object>>>> getCollectOrders(ReqBo reqBo) {
		ResBo<Pager<List<Map<Object, Object>>>> resBo=new ResBo<Pager<List<Map<Object, Object>>>>();
		@SuppressWarnings("unchecked")
		List<Integer> statuslist=reqBo.getParamT("status", List.class);
		Pager<List<Map<Object, Object>>> pager=orderComponent.getCollectOrders(reqBo.getParamInt("state"),reqBo.getParamStr("sort"),reqBo.getParamStr("column"),reqBo.getParamLong("userId"),reqBo.getParamStr("mobile"),reqBo.getParamStr("orderNum"),reqBo.getParamInt("timeType"),statuslist,reqBo.getParamInt("isPaid"),reqBo.getParamInt("page"),reqBo.getParamInt("rows"));
		List<Map<Object, Object>> list=new ArrayList<Map<Object, Object>>();
		list=pager.getEntry();
		for (Map<Object, Object> map : list) {
			List<Map<Object,Object>> orderDetailList=orderDetailComponent.selectOrderDetailList(Long.valueOf(map.get("id").toString()));
			map.put("orderDetailList", orderDetailList);
		}
		resBo.setResult(pager);
		return resBo;
	}


	@Override
	public ResBo<Pager<List<Map<Object, Object>>>> selectPurchasAgentsOrders(
			ReqBo reqBo) {
		ResBo<Pager<List<Map<Object, Object>>>> resBo=new ResBo<Pager<List<Map<Object, Object>>>>();
		@SuppressWarnings("unchecked")
		List<Integer> statuslist=reqBo.getParamT("status", List.class);
		Pager<List<Map<Object, Object>>> pager=orderComponent.selectPurchasAgentsOrders(reqBo.getParamInt("state"),reqBo.getParamStr("sort"),reqBo.getParamStr("column"),reqBo.getParamLong("userId"),reqBo.getParamStr("mobile"),reqBo.getParamStr("orderNum"),reqBo.getParamInt("timeType"),statuslist,reqBo.getParamInt("isPaid"),reqBo.getParamInt("page"),reqBo.getParamInt("rows"));
		List<Map<Object, Object>> list=new ArrayList<Map<Object, Object>>();
		list=pager.getEntry();
		for (Map<Object, Object> map : list) {
			List<Map<Object,Object>> orderDetailList=orderDetailComponent.selectOrderDetailList(Long.valueOf(map.get("id").toString()));
			map.put("orderDetailList", orderDetailList);
		}
		resBo.setResult(pager);
		return resBo;
	}
	
	@Override
	public ResBo<Map<Object, Object>> getOrderInfo(
			ReqBo reqBo) {
		ResBo<Map<Object, Object>> resBo=new ResBo<Map<Object, Object>>();
		Map<Object, Object> orderInfo=orderComponent.selectOrderInfo(reqBo.getParamLong("userId"),reqBo.getParamLong("id"));
		if(orderInfo != null){
			List<Map<Object,Object>> orderDetailList=orderDetailComponent.selectOrderDetailInfoList(Long.valueOf(orderInfo.get("id").toString()));
			orderInfo.put("orderDetailList", orderDetailList);
		}			
		resBo.setResult(orderInfo);	
		return resBo;
	}

	@Override
	public ResBo<Map<String, Integer>> getOrderCount(ReqBo reqBo) {
		ResBo<Map<String, Integer>> resBo=new ResBo<Map<String, Integer>>();
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("obligation", orderComponent.selectOrderByIsPaid1(reqBo.getParamLong("userId")));
		map.put("confirm", orderComponent.selectOrderByStatus4(reqBo.getParamLong("userId")));
		resBo.setResult(map);
		return resBo;
	}

	@Override
	public ResBo<Boolean> cancelOrder(ReqBo reqBo) {
		Long orderId = reqBo.getParamLong("orderId");
		Long userId = reqBo.getParamLong("userId");
		if(orderId == null||userId == null){
			return new ResBo<Boolean>(40L);
		}
        return new ResBo<Boolean>(orderComponent.deleteOrder(orderId, userId));
	}

	@Override
	public ResBo<Boolean> updateOrderStatus(ReqBo reqBo) {
		Long orderId = reqBo.getParamLong("orderId");
		OrderStatusEnum e = reqBo.getReqModel(OrderStatusEnum.class);
		if(orderId == null){
			return new ResBo<Boolean>(40L);
		}
		if(e == null){
			return new ResBo<Boolean>(41L);
		}
		return new ResBo<Boolean>(orderComponent.updateOrderStatus(orderId, e));
	}
	
	@Override
	public ResBo<Orders> getOrderByOrderNum(ReqBo reqBo) {
		String orderNum = reqBo.getParamStr("orderNum");
		return new ResBo<Orders>(orderComponent.selectOrdersByOrderNum(orderNum));
	}

	@Override
	public ResBo<Boolean> confirmOrder(ReqBo reqBo) {
		long orderId = reqBo.getParamLong("orderId");
		return new ResBo<Boolean>(orderComponent.updateOrderStatus(orderId, OrderStatusEnum.status_5));
	}

	@Override
	public ResBo<Boolean> confirmOrderDetail(ReqBo reqBo) {
		long detailId = reqBo.getParamLong("detailId");
		return new ResBo<Boolean>(orderDetailComponent.updateConfirmDetail(detailId));
	}

	@Override
	public ResBo<Boolean> confirmPaid(ReqBo reqBo) {
		long orderId = reqBo.getParamLong("orderId");
		Orders order = orderComponent.selectOrdersById(orderId);
		if(order.getIsPaid().equals(IsPaidEnum.IsPaid_2.getCode())){
			return new ResBo<Boolean>(true);
		}
		BigDecimal price = reqBo.getParamDecimal("price");
		return new ResBo<Boolean>(orderComponent.updateConfirmPaid(orderId, price));
	}

	@Override
	public ResBo<Map<Object, Object>> getCollectOrderInfo(ReqBo reqBo) {
		ResBo<Map<Object, Object>> resBo=new ResBo<Map<Object, Object>>();
		Map<Object, Object> orderInfo=orderComponent.selectCollectOrderInfo(reqBo.getParamLong("userId"),reqBo.getParamLong("id"),reqBo.getParamInt("pavilionId"));
		if(orderInfo != null){
			List<Map<Object,Object>> orderDetailList=orderDetailComponent.selectOrderDetailInfoList(Long.valueOf(orderInfo.get("id").toString()));
			orderInfo.put("orderDetailList", orderDetailList);
		}
			
		resBo.setResult(orderInfo);	
		return resBo;
	}

	@Override
	public ResBo<Map<Object, Object>> getPurchasAgentsOrderInfo(ReqBo reqBo) {
		ResBo<Map<Object, Object>> resBo=new ResBo<Map<Object, Object>>();
		Map<Object, Object> orderInfo=orderComponent.selectPurchasAgentsOrderInfo(reqBo.getParamLong("userId"),reqBo.getParamLong("id"),reqBo.getParamInt("pavilionId"));
		if(orderInfo != null){
			List<Map<Object,Object>> orderDetailList=orderDetailComponent.selectOrderDetailInfoList(Long.valueOf(orderInfo.get("id").toString()));
			orderInfo.put("orderDetailList", orderDetailList);
		}
		resBo.setResult(orderInfo);	
		return resBo;
	}

	@Override
	public ResBo<Pager<List<Map<Object, Object>>>> getPavilionOrders(ReqBo reqBo) {
		ResBo<Pager<List<Map<Object, Object>>>> resBo=new ResBo<Pager<List<Map<Object, Object>>>>();
		@SuppressWarnings("unchecked")
		List<Integer> statuslist=reqBo.getParamT("status", List.class);
		Pager<List<Map<Object, Object>>> pager=orderComponent.selectPavilionOrders(reqBo.getParamInt("state"),reqBo.getParamStr("sort"),reqBo.getParamStr("column"),reqBo.getParamLong("userId"),reqBo.getParamStr("mobile"),reqBo.getParamStr("orderNum"),reqBo.getParamInt("timeType"),statuslist,reqBo.getParamInt("isPaid"),reqBo.getParamInt("page"),reqBo.getParamInt("rows"));
		List<Map<Object, Object>> list= new ArrayList<Map<Object, Object>>();
		list=pager.getEntry();
		for (Map<Object, Object> map : list) {
			List<Map<Object,Object>> orderDetailList=orderDetailComponent.selectOrderDetailList(Long.valueOf(map.get("id").toString()));
			map.put("orderDetailList", orderDetailList);
		}
		resBo.setResult(pager);
		return resBo;
	}

	@Override
	public ResBo<Map<Object, Object>> getPavilionOrderInfo(ReqBo reqBo) {
		ResBo<Map<Object, Object>> resBo=new ResBo<Map<Object, Object>>();
		Map<Object, Object> orderInfo=orderComponent.getPavilionOrderInfo(reqBo.getParamLong("id"));
		if(orderInfo != null){
			List<Map<Object,Object>> orderDetailList=orderDetailComponent.selectOrderDetailInfoList(Long.valueOf(orderInfo.get("id").toString()));
			orderInfo.put("orderDetailList", orderDetailList);
		}
		resBo.setResult(orderInfo);	
		return resBo;
	}
	
	@Override
	public ResBo<List<Map<Object, Object>>> getTodayDsOrders(ReqBo reqBo){
		return new ResBo<List<Map<Object, Object>>>(orderComponent.getTodayDsOrders(reqBo.getParamInt("pavilionId")));
	}
	
	@Override
	public ResBo<List<Map<Object, Object>>> getTodayDgOrders(ReqBo reqBo){
		return new ResBo<List<Map<Object, Object>>>(orderComponent.getTodayDgOrders(reqBo.getParamLong("userId")));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResBo<List<Map<Object, Object>>> selectOrderDetailByOrderIds(ReqBo reqBo){
		return new ResBo<List<Map<Object, Object>>>(orderDetailComponent.selectOrderDetailByOrderIds((List<Long>)reqBo.getReqModel()));
	}
	
	@Override
	public ResBo<BigDecimal> getTodayTotalByUserId(ReqBo reqBo) {
		BigDecimal todayTotal = orderComponent.selectTodayTotalByUserId(reqBo.getParamLong("userId"));
		return new ResBo<BigDecimal>(todayTotal);
	}
	
	public ResBo<Boolean> canUseCoupon(ReqBo reqBo){
		Coupon coupon = (Coupon)reqBo.getReqModel();
		if(coupon == null){
			return new ResBo<Boolean>("优惠券不存在，请选择优惠券！");
		}
		
		return null;
	}

	@Override
	public ResBo<Integer> updateOrderPayByOrderId(ReqBo reqBo) {			
		Integer i = orderComponent.updateOrderPayByOrderId(reqBo.getParamStr("buyer_email"),reqBo.getParamLong("orderId"));
		return new ResBo<Integer>(i); 
	}

	@Override
	public ResBo<Map<Object, Object>> getReturnOrderInfo(ReqBo reqBo) {		
		return new ResBo<Map<Object, Object>>(orderDetailComponent.getReturnOrderInfo(reqBo.getParamLong("userId"), reqBo.getParamLong("createUserId"), reqBo.getParamLong("orderDetailId")));
	}

	@Override
	public ResBo<Map<Object, Object>> getOrderInfoByOrderDetailId(ReqBo reqBo) {		
		return new ResBo<Map<Object, Object>>(orderDetailComponent.getOrderInfoByOrderDetailId(reqBo.getParamLong("orderDetailId")));
	}

	@Override
	public ResBo<Integer> saveReturnGoods(ReqBo reqBo) {	
		Integer i = orderRefundComponent.saveReturnGoods(reqBo.getReqModel(OrderRefund.class));
		ResBo<Integer> resBo = new ResBo<Integer>(i);
		if(i != 1){
			resBo.setSuccess(false);
		}
		return resBo;
	}

	@Override
	public ResBo<Integer> updateReturnGoodsByODId(ReqBo reqBo) {
		Integer i = orderRefundComponent.updateReturnGoodsByODId(reqBo.getParamLong("orderId"),reqBo.getParamLong("orderDetailId"), reqBo.getParamInt("isRefund"), reqBo.getParamInt("state"));
		ResBo<Integer> resBo = new ResBo<Integer>(i);
		if(i != 1){
			resBo.setSuccess(false);
		}
		return resBo;
	}
}
