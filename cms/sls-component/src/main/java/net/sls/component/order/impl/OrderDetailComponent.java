package net.sls.component.order.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.order.IOrderDetailComponent;
import net.sls.dao.ext.order.OrderDetailMapperExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.web.Pager;

@Component
public class OrderDetailComponent implements IOrderDetailComponent{

	@Autowired
	private OrderDetailMapperExt orderDetailMapperExt;
	@Override
	public Pager<List<Map<String, Object>>> selectOrderDetailList(
			Integer orderId,Integer start, Integer number) {
		long count = orderDetailMapperExt.countOrderDetailsByFilter(orderId);
		List<Map<String,Object>> mapList = orderDetailMapperExt.selectOrderDetailList(orderId, (start-1)*number, number);
		Pager<List<Map<String, Object>>> pager = new Pager<List<Map<String, Object>>>();
		pager.setEntry(mapList);
		pager.setTotal(count);
		return pager;
	}

}
