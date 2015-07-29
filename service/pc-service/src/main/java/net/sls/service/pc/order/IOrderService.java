package net.sls.service.pc.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sls.dto.pc.order.Orders;
import net.sls.dto.pc.order.ResOrder;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IOrderService {
	/**
	 * 我的订单
	 * @param userId
	 * @param orderNum
	 * @param status
	 * @param isPaid
	 * @param start
	 * @param number
	 * @return
	 */
	public ResBo<Pager<List<Map<Object,Object>>>>  selectOrderListsByUserId(ReqBo reqBo);
	
	/**
	 * @param reqBo 
	 * @return ResBo<SettlementsDto>
	 * @date 2015年1月6日 下午4:29:45
	 */
	public ResBo<ResOrder> insertOrderCommit(ReqBo reqBo);

	/**
	 * 代购订单
	 * @param reqBo
	 * @return ResBo<Pager<List<Map<Object,Object>>>>
	 */
	public ResBo<Pager<List<Map<Object,Object>>>>  selectPurchasAgentsOrders(ReqBo reqBo);
	/**
	 * 代收订单
	 * @param reqBo
	 * @return ResBo<Pager<List<Map<Object,Object>>>>
	 */
	public ResBo<Pager<List<Map<Object,Object>>>> getCollectOrders(ReqBo reqBo);
	/**
	 * 亭子订单
	 * @param reqBo
	 * @return
	 */
	public ResBo<Pager<List<Map<Object,Object>>>> getPavilionOrders(ReqBo reqBo);
	/**
	 * 我的订单里查看订单
	 * @param reqBo
	 * @return
	 */
	public ResBo<Map<Object, Object>> getOrderInfo(ReqBo reqBo);
	/**
	 * 亭子订单查看订单
	 * @param reqBo
	 * @return
	 */
	public ResBo<Map<Object, Object>> getPavilionOrderInfo(ReqBo reqBo);
	/**
	 * 代收订单里查看订单
	 * @param reqBo
	 * @return
	 */
	public ResBo<Map<Object, Object>> getCollectOrderInfo(ReqBo reqBo);
	/**
	 * 代购订单里查看订单
	 * @param reqBo
	 * @return
	 */
	public ResBo<Map<Object, Object>> getPurchasAgentsOrderInfo(ReqBo reqBo);
	/**
	 * 待付款  未确定付款数量统计
	 * @param reqBo
	 * @return
	 */
	public ResBo<Map<String, Integer>> getOrderCount(ReqBo reqBo);
	
	/**
	 * 取消订单 
	 * 
	 * @param reqBo orderId 订单号
	 * @Parma ResBo boolean true false 
	 * */
	public ResBo<Boolean> cancelOrder(ReqBo reqBo);
	
	/**
	 * 修改订单状态 
	 * 
	 * @param reqBo orderId 订单号 modal OrderStatus instance
	 * @Parma ResBo boolean true false 
	 * */
	public ResBo<Boolean> updateOrderStatus(ReqBo reqBo);
	/**
	 *  根据订单号获取订单
	 * @param reqBo
	 * @return
	 */
	public ResBo<Orders> getOrderByOrderNum(ReqBo reqBo);
	
	/**
	 * 
	 * 确认收货
	 * 
	 * */
	public ResBo<Boolean> confirmOrder(ReqBo reqBo);
	
	/**
	 * 
	 * 订单详情单个去人收货
	 * 
	 * */
	public ResBo<Boolean> confirmOrderDetail(ReqBo reqBo);
	
	/**
	 * 根据orderId,price 确认付款
	 * */
	public ResBo<Boolean> confirmPaid(ReqBo reqBo);

	/**
	 * 得到今日的代收订单(昨天19点以后到当前时间)
	 * @param pavilionId
	 * @return
	 */
	public ResBo<List<Map<Object, Object>>> getTodayDsOrders(ReqBo reqBo);
	
	/**
	 * 得到今日的代购订单(昨天19点以后到当前时间)
	 * @param pavilionId
	 * @return
	 */
	public ResBo<List<Map<Object, Object>>> getTodayDgOrders(ReqBo reqBo);

	/**
	 * 根据订单ids得到订单详细信息
	 * @param orderIds
	 * @return
	 */
	public ResBo<List<Map<Object, Object>>> selectOrderDetailByOrderIds(ReqBo reqBo);
	
	/**
	 * 查询亭子用户今日消费总额以及余额
	 * @param reqBo
	 * @return
	 */
	public ResBo<BigDecimal> getTodayTotalByUserId(ReqBo reqBo);
	
	/**
	 * alipay支付成功添加支付账户
	 * @param reqBo
	 * @return
	 */
	public ResBo<Integer> updateOrderPayByOrderId(ReqBo reqBo);
	
	/**
	 * 退换货获取订单信息（展示）
	 * @param reqBo
	 * @return
	 */
	public ResBo<Map<Object, Object>> getReturnOrderInfo(ReqBo reqBo);
	
	/**
	 * 根据订单详情id获取退换货订单信息（保存）
	 * @param reqBo
	 * @return
	 */
	public ResBo<Map<Object, Object>> getOrderInfoByOrderDetailId(ReqBo reqBo);
	
	/**
	 * 保存退换货订单信息
	 * @param reqBo
	 * @return
	 */
	public ResBo<Integer> saveReturnGoods(ReqBo reqBo);
	
	/**
	 * 取消退换货申请
	 * @param reqBo
	 * @return
	 */
	public ResBo<Integer> updateReturnGoodsByODId(ReqBo reqBo);
}
