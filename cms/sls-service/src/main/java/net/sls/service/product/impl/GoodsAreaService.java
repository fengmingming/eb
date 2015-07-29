package net.sls.service.product.impl;

import java.util.List;
import net.sls.component.product.IGoodsAreaComponent;
import net.sls.dto.product.GoodsArea;
import net.sls.service.product.IGoodsAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class GoodsAreaService implements IGoodsAreaService{

	@Autowired
	public IGoodsAreaComponent goodsAreaComponent;

	@Override
	public ResBo<Pager<List<GoodsArea>>> selectAreaPager(ReqBo reqBo){
		return new ResBo<Pager<List<GoodsArea>>>(goodsAreaComponent.selectAreaPager(reqBo.getParamLong("goodsId")));
	}

	@Override
	public ResBo<?> deleteGoodsAreaById(ReqBo reqBo) {
		goodsAreaComponent.deleteGoodsAreaById(reqBo.getParamLong("id"));
		return new ResBo<Object>();
	}

}
