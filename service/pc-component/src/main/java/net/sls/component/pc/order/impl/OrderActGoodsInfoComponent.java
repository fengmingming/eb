package net.sls.component.pc.order.impl;

import net.sls.component.pc.order.IOrderActGoodsInfoComponent;
import net.sls.dao.pc.order.PCOrderActGoodsInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class OrderActGoodsInfoComponent implements IOrderActGoodsInfoComponent{
	
	@Autowired
	private PCOrderActGoodsInfoMapper orderActGoodsInfoMapper;
	@Override
	public int selectNumber(Long actId, Long goodsId) {
		return orderActGoodsInfoMapper.selectNumber(actId,goodsId);
	}

}
