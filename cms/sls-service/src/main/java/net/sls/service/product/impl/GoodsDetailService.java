package net.sls.service.product.impl;

import net.sls.component.product.IGoodsDetailComponent;
import net.sls.dto.product.GoodsDetail;
import net.sls.service.product.IGoodsDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class GoodsDetailService implements IGoodsDetailService{

	@Autowired
	public IGoodsDetailComponent goodsDetailComponent;
	

	@Override
	public ResBo<GoodsDetail> selectGoodsDetailById(ReqBo reqBo) {
		return new ResBo<GoodsDetail>(goodsDetailComponent.selectGoodsDetailById(reqBo.getParamLong("goodsId")));
	}

}
