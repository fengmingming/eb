package net.sls.component.product.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.product.IGoodsStockComponent;
import net.sls.dao.ext.product.GoodsStockMapperExt;
import net.sls.dao.product.GoodsStockMapper;
import net.sls.dto.product.GoodsStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

/**
 * 品类组件的实现类
 *
 */
@Component
public class GoodsStockComponent implements IGoodsStockComponent {

	@Autowired
	private GoodsStockMapper goodsStockMapper;

	@Autowired
	private GoodsStockMapperExt goodsStockMapperExt;

	@Override
	public void updateGoodsStock(GoodsStock goodsStock) {
		int i = goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
		if (i != 1) {
			throw new BusinessException(2L);
		}

	}

	@Override
	public Pager<List<Map<String, Object>>> selectGoodsStocks(String areaCode,Long goodsId,
			String goodsName, String sku, Integer providerId, Integer number,
			Integer number2, int page, int rows) {
		return new Pager<List<Map<String, Object>>>(
				goodsStockMapperExt
						.selectGoodsStocksByFilter(areaCode,goodsId, goodsName, sku,
								providerId, number, number2, (page-1)*rows, rows),
				goodsStockMapperExt.countGoodsStocksByFilter(areaCode,goodsId,
						goodsName, sku, providerId, number, number2));
	}

}
