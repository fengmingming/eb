package net.sls.service.order;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import util.model.ComboxDto;
import net.sls.dto.order.OrderLog;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IOrderService {
	/**
	  * 
	  * 查询订单列表
	  * 
	  * */
	 public ResBo<Pager<List<Map<Object,Object>>>> selectOrderList(ReqBo reqBo);
	 
	 /**
	  * 
	  * 查询结果集导出excel
	  * */
	 public ResBo<Workbook> exportExcel(ReqBo reqBo);
	 /**
	 * 查询订单信息及用户信息列表
	 * @param reqBo
	 * @return
	 */
	 public	ResBo<Map<String,Object>> selectOrderDetail(ReqBo reqBo);
	 /**
	 * 订单详情里支付功能（只更改状态并记录日志）
	 * @param reqBo
	 * @return
	 */
	 public ResBo<?> updateOrderStatus(ReqBo reqBo);
	 
	 public ResBo<Pager<List<OrderLog>>> selectOrderLog(ReqBo reqBo);
	 
	 public ResBo<?> updateOrderPaidStatus(ReqBo reqBo);
	 
	 public ResBo<List<ComboxDto>> selectPavilionCombox(ReqBo reqBo);
	 
	 public ResBo<List<Map<String,Object>>> selectOrderActGoodsInfo(ReqBo reqBo);

	 /**@author wangshaohui
	 * @Description: TODO 修改发货地址
	 * @param reqBo
	 * @return ResBo<?>
	 * @date 2015年5月18日 下午4:38:08
	 */
	public ResBo<?> updateDeliveryAddress(ReqBo reqBo);
	
	public ResBo<?> orderInfoByOrderNum(ReqBo reqBo);
	
	public ResBo<?> commitOrder(ReqBo reqBo);
	
	public ResBo<Pager<List<Map<String,Object>>>> refunds(ReqBo reqBo);
	
	public ResBo<?> updateRefund(ReqBo reqBo);
	
	public ResBo<?> completeRefund(ReqBo reqBo);
	
	 public ResBo<Workbook> refundExcel(ReqBo reqBo);
}
