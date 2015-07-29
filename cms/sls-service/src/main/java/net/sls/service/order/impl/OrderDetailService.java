package net.sls.service.order.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.order.IOrderDetailComponent;
import net.sls.service.order.IOrderDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class OrderDetailService implements IOrderDetailService{

	@Autowired
	private IOrderDetailComponent orderDetailComponent;
	
	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectOrderDetailList(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String,Object>>>>(orderDetailComponent.selectOrderDetailList(reqBo.getParamInt("orderId"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}

}
