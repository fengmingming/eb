package net.sls.component.product.impl;

import net.sls.component.product.IGoodsDetailComponent;
import net.sls.dao.product.GoodsDetailMapper;
import net.sls.dto.product.GoodsDetail;
import net.sls.dto.product.GoodsDetailExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsDetailComponent implements IGoodsDetailComponent {

	@Autowired
	public GoodsDetailMapper goodsDetailMapper;
	
	@Override
	public GoodsDetail selectGoodsDetailById(long goodsId) {
		GoodsDetailExample e = new GoodsDetailExample();
		e.createCriteria().andGoodsIdEqualTo(goodsId);
		return goodsDetailMapper.selectByExample(e).get(0);
	}

}
