package net.sls.dao.ext.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GoodsStockMapperExt {

	public List<Map<String, Object>> selectGoodsStocksByFilter(
			@Param("areaCode") String areaCode, @Param("goodsId") Long goodsId,
			@Param("goodsName") String goodsName, @Param("sku") String sku,
			@Param("providerId") Integer providerId,
			@Param("number") Integer number, @Param("number2") Integer number2,
			@Param("page") int page, @Param("rows") int rows);

	public long countGoodsStocksByFilter(@Param("areaCode") String areaCode,
			@Param("goodsId") Long goodsId,
			@Param("goodsName") String goodsName, @Param("sku") String sku,
			@Param("providerId") Integer providerId,
			@Param("number") Integer number, @Param("number2") Integer number2);
}