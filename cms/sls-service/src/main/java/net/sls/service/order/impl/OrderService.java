package net.sls.service.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.order.IDeliveryAddressComponent;
import net.sls.component.order.IOrderComponent;
import net.sls.component.product.IAreaComponent;
import net.sls.component.product.impl.CategoryComponent;
import net.sls.component.user.IMemberComponent;
import net.sls.dto.ext.order.CommitOrder;
import net.sls.dto.ext.order.ExportOrderQueryDto;
import net.sls.dto.order.DeliveryAddress;
import net.sls.dto.order.OrderDetail;
import net.sls.dto.order.OrderLog;
import net.sls.dto.order.OrderPay;
import net.sls.dto.order.OrderRefund;
import net.sls.dto.order.Orders;
import net.sls.dto.user.CmsUser;
import net.sls.dto.user.User;
import net.sls.service.order.IOrderService;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.framework.SessionUtil;
import util.model.ComboxDto;
import util.model.IsPaidEnum;
import util.model.OrderConst;
import util.model.OrderStateEnum;
import util.model.OrderStatusEnum;
import util.model.PayEnum;
import util.model.ShopTypeEnum;
import framework.exception.BusinessException;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderComponent orderComponent;
	@Autowired
	private IMemberComponent memberComponent;
	@Autowired
	private CategoryComponent categoryComponent;
	@Autowired
	private IDeliveryAddressComponent deliveryAddressComponent;
	@Autowired
	private IAreaComponent areaComponent;

	@Override
	public ResBo<Pager<List<Map<Object, Object>>>> selectOrderList(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<Object, Object>>>>(
				orderComponent.selectOrderList(reqBo.getParamStr(BusinessContant.OPERAREAID),reqBo.getParamStr("userName"),
						reqBo.getParamStr("mobile"),
						reqBo.getParamStr("orderNum"),
						reqBo.getParamInt("isPaid"),
						reqBo.getParamInt("state"),
						reqBo.getParamInt("status"),
						reqBo.getParamInt("pavilionId"),
						reqBo.getParamDate("startDate"),
						reqBo.getParamDate("endDate"),
						reqBo.getParamInt("isPaviOrder"),
						reqBo.getParamInt("isMobile"),
						reqBo.getParamInt("type"), reqBo.getParamInt("page"),
						reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<Map<String, Object>> selectOrderDetail(ReqBo reqBo) {
		long orderId = reqBo.getParamLong("orderId");
		Orders orderInfo = orderComponent.selectOrderInfo(orderId);
		User user = memberComponent.selectByPrimaryKey(orderInfo.getUserId());
		DeliveryAddress deliveryAddress = deliveryAddressComponent.selectAddressByOrderId(orderId);
		Map<String, Object> couponMap = orderComponent.selectOrderCoupon(orderId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", orderInfo);
		map.put("user", user);
		map.put("orderAddress", deliveryAddress);
		map.put("coupon", couponMap);
		return new ResBo<Map<String, Object>>(map);
	}

	@Override
	public ResBo<?> updateOrderStatus(ReqBo reqBo) {
		Orders order = reqBo.getReqModel(Orders.class);
		int i = orderComponent.updateOrderStatus(order);
		if (i == 1) {
			OrderLog ol = new OrderLog();
			CmsUser cu = (CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
			ol.setOrderId(order.getId());
			ol.setOperId(cu.getId());
			ol.setOperName(cu.getUserName());
			ol.setOperType(BusinessContant.ORDEROPTYPE_CMS);
			ol.setRemark("修改订单状态");
			ol.setCreateTime(new Date());
			orderComponent.insertOrdersLog(ol);
		} else {
			throw new BusinessException(2L);
		}
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> updateOrderPaidStatus(ReqBo reqBo) {
		Orders order = reqBo.getReqModel(Orders.class);
		int i = orderComponent.updateOrderStatus(order);
		if (i == 1) {
			OrderLog ol = new OrderLog();
			CmsUser cu = (CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
			ol.setOrderId(order.getId());
			ol.setOperId(cu.getId());
			ol.setOperName(cu.getUserName());
			ol.setOperType(BusinessContant.ORDEROPTYPE_CMS);
			ol.setRemark("修改订单付款状态");
			ol.setCreateTime(new Date());
			orderComponent.insertOrdersLog(ol);
		} else {
			throw new BusinessException(2L);
		}
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Pager<List<OrderLog>>> selectOrderLog(ReqBo reqBo) {
		return new ResBo<Pager<List<OrderLog>>>(
				orderComponent.selectOrderLog(reqBo.getParamLong("orderId")));
	}

	@Override
	public ResBo<Workbook> exportExcel(ReqBo reqBo) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer hasDetail = reqBo.getParamInt("hasDetail");
		List<ExportOrderQueryDto> list = orderComponent.selectExportExcel(reqBo.getParamStr(BusinessContant.OPERAREAID),
				reqBo.getParamStr("userName"), reqBo.getParamStr("mobile"),
				reqBo.getParamStr("orderNum"), reqBo.getParamInt("isPaid"),
				reqBo.getParamInt("state"), reqBo.getParamInt("status"),
				reqBo.getParamInt("pavilionId"),
				reqBo.getParamDate("startDate"), reqBo.getParamDate("endDate"),
				reqBo.getParamInt("isPaviOrder"),reqBo.getParamInt("isMobile"),reqBo.getParamInt("type"),hasDetail);
		Workbook book = new XSSFWorkbook();
		Sheet sheet = book.createSheet();
		Row row = null;
		ExportOrderQueryDto dto = null;
		row = sheet.createRow(0);
		row.createCell(0).setCellValue("订单号");
		row.createCell(1).setCellValue("订单处理状态");
		row.createCell(2).setCellValue("订单状态");
		row.createCell(3).setCellValue("是否付款");
		row.createCell(4).setCellValue("创建时间");
		row.createCell(5).setCellValue("总金额");
		row.createCell(6).setCellValue("已付金额");
		row.createCell(7).setCellValue("应付金额");
		row.createCell(8).setCellValue("优惠金额");
		row.createCell(9).setCellValue("使用余额");
		row.createCell(10).setCellValue("提货方式");
		row.createCell(11).setCellValue("订单类型");
		row.createCell(12).setCellValue("亭子名称");
		row.createCell(13).setCellValue("亭子code");
		row.createCell(14).setCellValue("购货人");
		row.createCell(15).setCellValue("收货地址");
		row.createCell(16).setCellValue("付款时间");
		row.createCell(17).setCellValue("支付方式");
		row.createCell(18).setCellValue("订单来源");
		if(hasDetail != null&&hasDetail == 1){
			row.createCell(19).setCellValue("商品名称");
			row.createCell(20).setCellValue("商品金额");
			row.createCell(21).setCellValue("供货商名称");
			row.createCell(22).setCellValue("数量");
			row.createCell(23).setCellValue("商品货号");
			row.createCell(24).setCellValue("一级品类");
			row.createCell(25).setCellValue("二级品类");
			row.createCell(26).setCellValue("三级品类");
			row.createCell(27).setCellValue("是否已提货");
		}
		for(int i = 0,j=list.size();i < j;i++){
			dto = list.get(i);
			row = sheet.createRow(i+1);
			row.createCell(0,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getOrderNum());
			row.createCell(1,XSSFCell.CELL_TYPE_STRING).setCellValue(OrderConst.orderStatusName(dto.getStatus()));
			row.createCell(2,XSSFCell.CELL_TYPE_STRING).setCellValue(OrderConst.orderStateName(dto.getState()));
			row.createCell(3,XSSFCell.CELL_TYPE_STRING).setCellValue(OrderConst.isPaidName(dto.getIsPaid()));
			row.createCell(4,XSSFCell.CELL_TYPE_STRING).setCellValue(format.format(dto.getCreatetime()));
			row.createCell(5,XSSFCell.CELL_TYPE_NUMERIC).setCellValue(dto.getAmount().doubleValue());
			row.createCell(6,XSSFCell.CELL_TYPE_NUMERIC).setCellValue(dto.getPaidPrice().doubleValue());
			row.createCell(7,XSSFCell.CELL_TYPE_NUMERIC).setCellValue(dto.getPayPrice().doubleValue());
			row.createCell(8,XSSFCell.CELL_TYPE_NUMERIC).setCellValue(dto.getDiscutPrice().doubleValue());
			row.createCell(9,XSSFCell.CELL_TYPE_NUMERIC).setCellValue(dto.getUseBalancePrice().doubleValue());
			row.createCell(10,XSSFCell.CELL_TYPE_STRING).setCellValue(OrderConst.deliveryTypeName(dto.getDeliveryType()));
			row.createCell(11,XSSFCell.CELL_TYPE_STRING).setCellValue(OrderConst.typeName(dto.getShopType()));
			row.createCell(12,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getPavilionName());
			row.createCell(13,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getPavilionCode());
			row.createCell(14,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getUserName());
			if(dto.getRemark() != null){
				row.createCell(15,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getRemark());
			}
			if(dto.getPayTime() != null){
				row.createCell(16,XSSFCell.CELL_TYPE_STRING).setCellValue(format.format(dto.getPayTime()));
			}
			row.createCell(17,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getPayName());
			row.createCell(18,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getChannelId() == 1?"电脑":"手机");
			if(hasDetail != null&&hasDetail == 1){
				row.createCell(19,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getGoodsName());
				row.createCell(20,XSSFCell.CELL_TYPE_NUMERIC).setCellValue(dto.getPrice().doubleValue());
				row.createCell(21,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getProviderName());
				row.createCell(22,XSSFCell.CELL_TYPE_NUMERIC).setCellValue(dto.getNumber());
				row.createCell(23,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getSku());
				if(dto.getOneId() != null){
					row.createCell(24,XSSFCell.CELL_TYPE_STRING).setCellValue(categoryComponent.selectCategoryNameById(dto.getOneId()));
				}
				if(dto.getTwoId() != null){
					row.createCell(25,XSSFCell.CELL_TYPE_STRING).setCellValue(categoryComponent.selectCategoryNameById(dto.getTwoId()));
				}
				if(dto.getThreeId() != null){
					row.createCell(26,XSSFCell.CELL_TYPE_STRING).setCellValue(categoryComponent.selectCategoryNameById(dto.getThreeId()));
				}
				if(dto.getIsDelivery() != null){
					row.createCell(27,XSSFCell.CELL_TYPE_STRING).setCellValue(dto.getIsDelivery().equals(1)?"是":"否");
				}
			}
		}
		return new ResBo<Workbook>(book);
	}

	@Override
	public ResBo<List<ComboxDto>> selectPavilionCombox(ReqBo reqBo) {
		return new ResBo<List<ComboxDto>>(orderComponent.selectPavilionComboxDto(reqBo.getParamStr("name"),reqBo.getParamStr("areaCode")));
	}

	@Override
	public ResBo<List<Map<String,Object>>> selectOrderActGoodsInfo(ReqBo reqBo) {
		return new ResBo<List<Map<String,Object>>>(orderComponent.selectOrderActGoodsInfo(reqBo.getParamLong("orderId")));
	}

	@Override
	public ResBo<?> updateDeliveryAddress(ReqBo reqBo) {
		Long orderId = reqBo.getParamLong("orderId");
		//封装地址
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setReceiver(reqBo.getParamStr("receiver"));
		deliveryAddress.setMobile(reqBo.getParamStr("mobile"));
		deliveryAddress.setRemark(reqBo.getParamStr("remark"));
		deliveryAddress.setId(reqBo.getParamLong("addressId"));
		deliveryAddress.setProvince(reqBo.getParamInt("province"));
		deliveryAddress.setCity(reqBo.getParamInt("city"));
		deliveryAddress.setDistrict(reqBo.getParamInt("district"));
		deliveryAddress.setCommunity(reqBo.getParamInt("community"));
		deliveryAddress.setPavilionId(reqBo.getParamInt("pavilionId"));
		
		deliveryAddress.setProvinceName(areaComponent.selectAreaNameById(deliveryAddress.getProvince()));
		deliveryAddress.setCityName(areaComponent.selectAreaNameById(deliveryAddress.getCity()));
		deliveryAddress.setDistrictName(areaComponent.selectAreaNameById(deliveryAddress.getDistrict()));
		if(deliveryAddress.getCommunity() != null){
			deliveryAddress.setCommunityName(areaComponent.selectAreaNameById(deliveryAddress.getCommunity()));
		}
		if(deliveryAddress.getPavilionId() != null){
			deliveryAddress.setPavilionName(areaComponent.selectAreaNameById(deliveryAddress.getPavilionId()));
			deliveryAddress.setPavilionCode(areaComponent.selectAreaCodeById(deliveryAddress.getPavilionId()));
		}
		//修改送货地址
		orderComponent.updateDeliveryAddress(deliveryAddress);
		
		//插入日志
		OrderLog ol = new OrderLog();
		CmsUser cu = (CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
		ol.setOrderId(orderId);
		ol.setOperId(cu.getId());
		ol.setOperName(cu.getUserName());
		ol.setOperType(BusinessContant.ORDEROPTYPE_CMS);
		ol.setRemark("系统修改收货地址信息");
		ol.setCreateTime(new Date());
		orderComponent.insertOrdersLog(ol);
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> orderInfoByOrderNum(ReqBo reqBo) {
		return new ResBo<Map<String,Object>>(orderComponent.selectOrderInfoByOrderNum(reqBo.getParamStr("orderNum")));
	}

	@Override
	public ResBo<?> commitOrder(ReqBo reqBo) {
		CommitOrder co = reqBo.getReqModel(CommitOrder.class);
		List<OrderDetail> list = co.getDetails();
		BigDecimal total = BigDecimal.ZERO;
		Date date = new Date();
		for(OrderDetail od:list){
			total = total.add(od.getPrice().multiply(new BigDecimal(od.getNumber())));
			od.setEffPay(od.getPrice());
			od.setCreateTime(date);
		}
		Orders order = new Orders();
		order.setAmount(total);
		order.setChannelId(3);
		order.setCreateTime(date);
		order.setCreateUserId(co.getUserId());
		order.setDeliveryType(co.getDeliveryType());
		if(BigDecimal.ZERO.equals(total)){
			order.setIsPaid(IsPaidEnum.IsPaid_2.getCode());
			order.setPaidPrice(total);
			order.setPayTime(date);
			order.setPayPrice(BigDecimal.ZERO);
		}else{
			order.setIsPaid(IsPaidEnum.IsPaid_1.getCode());
			order.setPayPrice(total);
		}
		order.setOrderNum(generateOrderNum(ShopTypeEnum.ShopType_1));
		order.setOrderPrice(total);
		order.setRemark(co.getOrderRemark());
		order.setShopType(ShopTypeEnum.ShopType_1.getCode());
		order.setState(OrderStateEnum.NORMAL.getCode());
		order.setStatus(OrderStatusEnum.status_2.getStatus());
		order.setUseBalancePrice(BigDecimal.ZERO);
		order.setUserId(co.getUserId());
		DeliveryAddress da = new DeliveryAddress();
		da.setCity(co.getCityId());
		da.setCityName(co.getCityName());
		da.setCommunity(co.getCommunityId());
		da.setCommunityName(co.getCommunityName());
		da.setDistrict(co.getDistrictId());
		da.setDistrictName(co.getDistrictName());
		da.setMobile(co.getMobile());
		da.setPavilionId(co.getPavilionId());
		da.setPavilionName(co.getPavilionName());
		da.setPavilionCode(areaComponent.selectAreaCodeById(co.getPavilionId()));
		da.setPostcode(co.getPostcode());
		da.setProvince(co.getProvinceId());
		da.setProvinceName(co.getProvinceName());
		da.setReceiver(da.getReceiver());
		da.setRemark(co.getRemark());
		OrderPay op = new OrderPay();
		op.setCreateTime(date);
		op.setUserId(co.getUserId());
		if(BigDecimal.ZERO.equals(total)){
			op.setPayCode(PayEnum.Balance.getId());
			op.setPayName(PayEnum.Balance.getName());
		}else{
			PayEnum pe = PayEnum.getPayEnum(co.getOrderWay());
			op.setPayCode(pe.getId());
			op.setPayName(pe.getName());
		}
		orderComponent.insertOrderInfo(order, list, da, op);
		return new ResBo<Object>(order.getOrderNum());
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
	public ResBo<Pager<List<Map<String, Object>>>> refunds(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String,Object>>>>(orderComponent.selectRefunds(reqBo.getParamStr("orderNum"), reqBo.getParamInt("state"), reqBo.getParamInt("type"), reqBo.getParamDate("startDate"), reqBo.getParamDate("endDate"), reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<?> updateRefund(ReqBo reqBo) {
		orderComponent.updateRefund(reqBo.getReqModel(OrderRefund.class));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> completeRefund(ReqBo reqBo) {
		orderComponent.updateCompleteRefund(reqBo.getParamLong("id"), reqBo.getParamInt("type"));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Workbook> refundExcel(ReqBo reqBo) {
		List<Map<String,Object>> list = orderComponent.selectRefundExcel(reqBo.getParamStr("orderNum"), reqBo.getParamInt("state"), reqBo.getParamInt("type"), reqBo.getParamDate("startDate"), reqBo.getParamDate("endDate"));
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(0);
		row.createCell(0, XSSFCell.CELL_TYPE_STRING).setCellValue("订单号");
		row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue("退换货商品");
		row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue("供货商");
		row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue("退换货数量");
		row.createCell(4, XSSFCell.CELL_TYPE_STRING).setCellValue("退款金额");
		row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue("创建时间");
		row.createCell(6, XSSFCell.CELL_TYPE_STRING).setCellValue("送货方式");
		row.createCell(7, XSSFCell.CELL_TYPE_STRING).setCellValue("省");
		row.createCell(8, XSSFCell.CELL_TYPE_STRING).setCellValue("市");
		row.createCell(9, XSSFCell.CELL_TYPE_STRING).setCellValue("区");
		row.createCell(10, XSSFCell.CELL_TYPE_STRING).setCellValue("取货方式");
		row.createCell(11, XSSFCell.CELL_TYPE_STRING).setCellValue("送货人电话");
		row.createCell(12, XSSFCell.CELL_TYPE_STRING).setCellValue("送货人名称");
		row.createCell(13, XSSFCell.CELL_TYPE_STRING).setCellValue("送货人详细地址");
		row.createCell(14, XSSFCell.CELL_TYPE_STRING).setCellValue("收货方式");
		row.createCell(15, XSSFCell.CELL_TYPE_STRING).setCellValue("省");
		row.createCell(16, XSSFCell.CELL_TYPE_STRING).setCellValue("市");
		row.createCell(17, XSSFCell.CELL_TYPE_STRING).setCellValue("区");
		row.createCell(18, XSSFCell.CELL_TYPE_STRING).setCellValue("收货亭子");
		row.createCell(19, XSSFCell.CELL_TYPE_STRING).setCellValue("收货人电话");
		row.createCell(20, XSSFCell.CELL_TYPE_STRING).setCellValue("收货人名称");
		row.createCell(21, XSSFCell.CELL_TYPE_STRING).setCellValue("收货人详细地址");
		row.createCell(22, XSSFCell.CELL_TYPE_STRING).setCellValue("退换货来源");
		int i = 0;
		for(Map<String,Object> m:list){
			i++;
			row = sheet.createRow(i);
			row.createCell(0, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("orderNum").toString());
			row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("goodsName").toString());
			if(m.get("providerName")!=null){
				row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("providerName").toString());
			}
			row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("number").toString());
			row.createCell(4, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("refundPrice").toString());
			row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("createTime").toString());
			if("1".equals(m.get("pickupWay").toString())){
				row.createCell(6, XSSFCell.CELL_TYPE_STRING).setCellValue("送至服务亭");
			}else{
				row.createCell(6, XSSFCell.CELL_TYPE_STRING).setCellValue("上门取");
			}
			row.createCell(7, XSSFCell.CELL_TYPE_STRING).setCellValue(areaComponent.selectAreaNameById(Integer.valueOf(m.get("provinceIdT").toString())));
			row.createCell(8, XSSFCell.CELL_TYPE_STRING).setCellValue(areaComponent.selectAreaNameById(Integer.valueOf(m.get("cityIdT").toString())));
			row.createCell(9, XSSFCell.CELL_TYPE_STRING).setCellValue(areaComponent.selectAreaNameById(Integer.valueOf(m.get("districtIdT").toString())));
			if(m.get("pavilionIdT") != null){
				row.createCell(10, XSSFCell.CELL_TYPE_STRING).setCellValue(areaComponent.selectAreaNameById(Integer.valueOf(m.get("pavilionIdT").toString())));
			}
			row.createCell(11, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("mobileT").toString());
			row.createCell(12, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("receiverT").toString());
			row.createCell(13, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("remarkT").toString());
			if("1".equals(m.get("deliveryType").toString())){
				row.createCell(14, XSSFCell.CELL_TYPE_STRING).setCellValue("自提");
			}else{
				row.createCell(14, XSSFCell.CELL_TYPE_STRING).setCellValue("送货上门");
			}
			row.createCell(15, XSSFCell.CELL_TYPE_STRING).setCellValue(areaComponent.selectAreaNameById(Integer.valueOf(m.get("provinceIdF").toString())));
			row.createCell(16, XSSFCell.CELL_TYPE_STRING).setCellValue(areaComponent.selectAreaNameById(Integer.valueOf(m.get("cityIdF").toString())));
			row.createCell(17, XSSFCell.CELL_TYPE_STRING).setCellValue(areaComponent.selectAreaNameById(Integer.valueOf(m.get("districtIdF").toString())));
			if(m.get("pavilionIdF") != null){
				row.createCell(18, XSSFCell.CELL_TYPE_STRING).setCellValue(areaComponent.selectAreaNameById(Integer.valueOf(m.get("pavilionIdF").toString())));
			}
			row.createCell(19, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("mobileF").toString());
			row.createCell(20, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("receiverF").toString());
			row.createCell(21, XSSFCell.CELL_TYPE_STRING).setCellValue(m.get("remarkF").toString());
			if("1".equals(m.get("origin").toString())){
				row.createCell(22, XSSFCell.CELL_TYPE_STRING).setCellValue("用户");
			}else{
				row.createCell(22, XSSFCell.CELL_TYPE_STRING).setCellValue("亭子");
			}
		}
		return new ResBo<Workbook>(wb);
	}

}
