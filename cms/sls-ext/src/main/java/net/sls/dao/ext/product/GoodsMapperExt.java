package net.sls.dao.ext.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sls.dto.product.Goods;

import org.apache.ibatis.annotations.Param;

import util.model.ComboxDto;

public interface GoodsMapperExt {
	
	int insertGoodsExt(Goods goods);
	
	public List<ComboxDto> selectGoodsListByProvider(@Param("providerId") Long providerId);
	
	/**
	 * @Description: TODO 查询产品包括详情，根据品类id，在后台系统上使用
	 * @param oneId
	 * @param twoId
	 * @param threeId
	 * @return List<Goods>
	 */
	public List<Goods> selectGoodsListByCategoryId(@Param("areaCode") String areaCode,@Param("providerId") Long providerId,@Param("oneId") Long oneId,@Param("twoId") Long twoId,@Param("threeId") Long threeId,@Param("start") Integer start,@Param("number") Integer number);

	/**
	 * @Description: TODO 查询产品不包括详情，根据品类id，查出它的记录条数，在后台系统上使用
	 * @param oneId
	 * @param twoId
	 * @param threeId
	 * @return long
	 */
	public long countGoodsListByCategoryId(@Param("areaCode") String areaCode,@Param("providerId") Long providerId,@Param("oneId") Long oneId,@Param("twoId") Long twoId,@Param("threeId") Long threeId);
	
	
	public long countGoodsByFilter(@Param("areaCode")String areaCode,@Param("goodsId")Long goodsId,@Param("sku")String sku,@Param("name")String name,@Param("price")BigDecimal price,@Param("price2")BigDecimal price2, @Param("provider")Integer provider,@Param("isSale")Integer isSale);
	
	/**
	 * 
	 * @param goodsId 商品id
	 * @param sku 商品sku
	 * @param name 商品名称
	 * @param price price2 价格区间
	 * @param brandId 供货商id
	 * 
	 * */
	public List<Map<String,Object>> selectGoodsByFilter(@Param("areaCode")String areaCode,@Param("goodsId")Long goodsId,@Param("sku")String sku,@Param("name")String name,@Param("price")BigDecimal price,@Param("price2")BigDecimal price2, @Param("provider")Integer provider,@Param("isSale")Integer isSale,@Param("start") int start, @Param("number") int number);
	
	public long countGoodsFragment(@Param("id")Long id,@Param("sku")String sku,@Param("goodsName")String goodsName);
	
	public Map<String,Object> selectUpdateGoodsInfo(long goodsId);
	
	public int updateBatchGoodsIsSale(@Param("ids")List<Long> ids,@Param("isSale")Integer isSale);
	
	public void flushBuyNum();
	
	public int batchUseCoupon(List<Long> ids);
	
	public int batchNoUseCoupon(List<Long> ids);
}
