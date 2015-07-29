package net.sls.dao.ext.product;

import java.util.List;

import net.sls.dto.product.GoodsCategory;

import org.apache.ibatis.annotations.Param;

public interface GoodsCategoryMapperExt {
	public List<GoodsCategory> selectGoodsCategorysByFilter(@Param("oneId") Long oneId,
			@Param("twoId") Long twoId,@Param("threeId") Long threeId,@Param("goodsId") Long goodsId,
			@Param("start") Integer start,@Param("number") Integer number);
	
	public long countGoodsCategorysByFilter(@Param("oneId") Long oneId,
			@Param("twoId") Long twoId,@Param("threeId") Long threeId,@Param("goodsId") Long goodsId);
	
	int updateByGoodsIdSelective(@Param("oneId") Long oneId,
			@Param("twoId") Long twoId,@Param("threeId") Long threeId,@Param("goodsId") Long[] goodsId);
	
}
