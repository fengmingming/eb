package net.sls.dao.pc.product;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.product.GoodsArea;
import net.sls.dto.pc.product.OrderStockDto;
import net.sls.dto.pc.product.SmallGoodsArea;

import org.apache.ibatis.annotations.Param;

public interface PCGoodsAreaMapper {

	public GoodsArea selectGoodsAreaByFilter(@Param("goodsId") Long goodId,
			@Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId,
			@Param("districtId") Integer districtId,
			@Param("communityId") Integer communityId,
			@Param("pavilionId") Integer pavilionId);

	public int updateGoodsArea(@Param("id") Long id, @Param("number") int num);

	public List<SmallGoodsArea> selectSmallGoodsAreas(Map<String,Object> map);
	
	public int updateGoodsAreaBatch(List<OrderStockDto> osds);

}
