package net.sls.component.pc.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sls.component.pc.product.IGoodsStockComponent;
import net.sls.dao.pc.product.PCGoodsStockMapper;
import net.sls.dto.pc.order.OrderDetail;
import net.sls.dto.pc.product.GoodsStock;
@Component
public class GoodsStockComponent implements IGoodsStockComponent{
	@Autowired
	private PCGoodsStockMapper goodsStockMapper;
	@Override
	public GoodsStock selectGoodsStock(Long goodId) {
		GoodsStock goodsStock=new GoodsStock();
		goodsStock=goodsStockMapper.selectGoodsStock(goodId);
		return goodsStock;
	}
	@Override
	public int updateGoodsStock(Long goodsId,Integer num) {
		return goodsStockMapper.updateGoodsStock(goodsId,num);
	}
	@Override
	public int updateGoodsStockAdd(Long goodsId, Integer num) {
		return goodsStockMapper.updateGoodsStockAdd(goodsId, num);
	}

	@Override
	public int updateGoodsStockAdd(List<OrderDetail> orderDetailList) {
		return goodsStockMapper.updateGoodsStockAdd(orderDetailList);
	}

}
