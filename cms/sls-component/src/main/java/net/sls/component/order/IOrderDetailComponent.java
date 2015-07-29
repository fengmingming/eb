package net.sls.component.order;

import java.util.List;
import java.util.Map;

import framework.web.Pager;

public interface IOrderDetailComponent {
	/**
	 * 
	 * 根据订单Id条件查询订单详情信息列表
	 * @return 
	 * */
	Pager<List<Map<String, Object>>> selectOrderDetailList(Integer orderId,Integer start, Integer number);

}
