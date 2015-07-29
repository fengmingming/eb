package net.sls.service.product.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.product.IGoodsStockComponent;
import net.sls.dto.product.GoodsStock;
import net.sls.service.product.IGoodsStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class GoodsStockService implements IGoodsStockService {

	@Autowired
	private IGoodsStockComponent goodsStockComponent;

	@Override
	public ResBo<GoodsStock> updateGoodsStock(ReqBo reqBo) {
		goodsStockComponent.updateGoodsStock(reqBo
				.getReqModel(GoodsStock.class));
		return new ResBo<GoodsStock>();
	}

	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectGoodsStocks(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String, Object>>>>(
				goodsStockComponent.selectGoodsStocks(
						reqBo.getParamStr("areaCode"),
						reqBo.getParamLong("goodsId"),
						reqBo.getParamStr("goodsName"),
						reqBo.getParamStr("sku"),
						reqBo.getParamInt("providerId"),
						reqBo.getParamInt("number"),
						reqBo.getParamInt("number2"),
						reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

}
