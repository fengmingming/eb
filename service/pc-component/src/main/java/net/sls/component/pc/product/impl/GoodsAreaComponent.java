package net.sls.component.pc.product.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sls.component.pc.product.IGoodsAreaComponent;
import net.sls.dao.pc.product.PCGoodsAreaMapper;
import net.sls.dto.pc.product.GoodsArea;
import net.sls.dto.pc.product.OrderStockDto;
import net.sls.dto.pc.product.SmallGoodsArea;

@Component
public class GoodsAreaComponent implements IGoodsAreaComponent {
	@Autowired
	PCGoodsAreaMapper goodsAreaMapper;

	@Override
	public GoodsArea selectGoodsAreaByFilter(Long goodId, Integer provinceId,
			Integer cityId, Integer districtId, Integer communityId, Integer pavilionId) {
		return goodsAreaMapper.selectGoodsAreaByFilter(goodId, provinceId,
				cityId, districtId, communityId, pavilionId);
	}

	@Override
	public int updateGoodsArea(Long id, Integer num) {
		return goodsAreaMapper.updateGoodsArea(id, num);
	}

	@Override
	public List<SmallGoodsArea> selectSmallGoodsAreas(Map<String, Object> map) {
		return goodsAreaMapper.selectSmallGoodsAreas(map);
	}

	@Override
	public int updateGoodsAreas(List<OrderStockDto> list) {
		return goodsAreaMapper.updateGoodsAreaBatch(list);
	}

}
