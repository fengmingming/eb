package net.sls.dao.pc.product;

import java.util.List;

import net.sls.dto.pc.product.CommodityDto;
import net.sls.dto.pc.product.OrderStockDto;
import net.sls.dto.pc.shopcart.GoodDto;
import net.sls.dto.pc.shopcart.SSettleGood;

import org.apache.ibatis.annotations.Param;

public interface PCGoodsMapper {
	
	/**@author wangshaohui
	 * @Description: TODO 查询产品包括详情，根据品类id
	 * @param first
	 * @param second
	 * @param third
	 * @Param areaIds 区域范围ids
	 * @Param actType 活动类型
	 * @Param hasGoods 是否仅显示有货，1表示仅显示有货
	 * @param minPrice 最低价钱
	 * @param maxPrice 最高价钱
	 * @param sortPrice 价钱的排序类型：0--从小到大，1--从大到小 ，null不排序
	 * @param sortNew 新品的排序类型：0--从新到旧，1--从旧到新，null不排序
	 * @param sortSale 销量的排序类型：0--从高到低，null不排序
	 * @param page 页数
	 * @param rows 每页显示的记录数据
	 * @return List<Map<Object,Object>>
	 */
	public List<CommodityDto> selectGoods(@Param("first") Long first,@Param("second") Long second,
			@Param("third") Long third,@Param("areaIds") List<Integer> areaIds,@Param("actType")Integer actType,
			@Param("hasGoods") Integer hasGoods,@Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice,@Param("sortPrice") Integer sortPrice,
			@Param("sortNew") Integer sortNew,@Param("sortSale") Integer sortSale,
			@Param("start") Integer start,@Param("number") Integer number);

	/**@author wangshaohui
	 * @Description: TODO 查询产品包括详情，根据品类id，查出它的记录条数
	 * @param first
	 * @param second
	 * @param third
	 * @Param areaIds 区域范围ids
	 * @Param actType 活动类型
	 * @Param hasGoods 是否仅显示有货，1表示仅显示有货
	 * @param minPrice 最低价钱
	 * @param maxPrice 最高价钱
	 * @param sortPrice 价钱的排序类型：0--从小到大，1--从大到小 ，null不排序
	 * @param sortNew 新品的排序类型：0--从新到旧，1--从旧到新，null不排序
	 * @param sortSale 销量的排序类型：0--从高到低，null不排序
	 * @return long
	 */
	public long countGoods(@Param("first") Long first,@Param("second") Long second,
			@Param("third") Long third,@Param("areaIds") List<Integer> areaIds, @Param("actType")Integer actType,
			@Param("hasGoods") Integer hasGoods,@Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice,@Param("sortPrice") Integer sortPrice,
			@Param("sortNew") Integer sortNew,@Param("sortSale") Integer sortSale);
	
	/**@author wangshaohui
	 * @Description: TODO 查询商品详情，根据商品id。查到的内容包括：四张小图、一张大图、商品描述、库存
	 * @param id id是商品id
	 * @return CommodityDto
	 */
	public CommodityDto selectGoodsDetail(@Param("id") Long id);
	/**
	 * 
	 * @param goodIdList
	 * @return
	 */
	public List<CommodityDto> selectGoodsCartDto(@Param("ids") List<Long> ids);
	/**
	 * 查看是否限购
	 * @param goodId
	 * @return 表里除了remark所有字段
	 */
	public GoodDto selectGoodDto(Long goodId);
	
	public List<SSettleGood> selectSSettleGoods(List<Long> gls);
	
	/**@author wangshaohui
	 * @Description: TODO 查询产品包括详情，根据品类id
	 * @Param contents 输入内容
	 * @Param areaIds 区域范围ids
	 * @Param hasGoods 是否仅显示有货，1表示仅显示有货
	 * @param minPrice 最低价钱
	 * @param maxPrice 最高价钱
	 * @param sortPrice 价钱的排序类型：0--从小到大，1--从大到小 ，null不排序
	 * @param sortNew 新品的排序类型：0--从新到旧，1--从旧到新，null不排序
	 * @param sortSale 销量的排序类型：0--从高到低，null不排序
	 * @param page 页数
	 * @param rows 每页显示的记录数据
	 * @return List<Map<Object,Object>>
	 */
	public List<CommodityDto> selectGoodsList(@Param("contents") String contents[],@Param("areaIds") List<Integer> areaIds,
			@Param("hasGoods") Integer hasGoods,@Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice,@Param("sortPrice") Integer sortPrice,
			@Param("sortNew") Integer sortNew,@Param("sortSale") Integer sortSale,
			@Param("start") Integer start,@Param("number") Integer number);

	/**@author wangshaohui
	 * @Description: TODO 查询产品包括详情，根据品类id，查出它的记录条数
	 * @Param contents 输入内容
	 * @Param areaIds 区域范围ids
	 * @Param hasGoods 是否仅显示有货，1表示仅显示有货
	 * @param minPrice 最低价钱
	 * @param maxPrice 最高价钱
	 * @param sortPrice 价钱的排序类型：0--从小到大，1--从大到小 ，null不排序
	 * @param sortNew 新品的排序类型：0--从新到旧，1--从旧到新，null不排序
	 * @param sortSale 销量的排序类型：0--从高到低，null不排序
	 * @return long
	 */
	public long countGoodsList(@Param("contents") String contents[],@Param("areaIds") List<Integer> areaIds,
			@Param("hasGoods") Integer hasGoods,@Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice,@Param("sortPrice") Integer sortPrice,
			@Param("sortNew") Integer sortNew,@Param("sortSale") Integer sortSale);
	
	
	/**
	 * 
	 * 提交订单专用，一般不要修改
	 * 
	 * */
	public int updateGoodsStcokBatch(List<OrderStockDto> dto);

	/**
	 * 根据商品id得到商品的提示信息（后台批量添加的）
	 * @param id
	 * @return
	 */
	public String selectGoodsDetailTip(long id);

	/**
	 * 根据商品Ids得到商品信息，并按照id的顺序排序（lucene搜索后使用）
	 * @param goodsIds
	 * @return
	 */
	public List<CommodityDto> selectGoodsByIds(@Param("goodsIds")List<Long> goodsIds);

	public long countDynpageGoods(@Param("dynpageId") Integer dynpageId);
	/**
	 * 
	 * @param dynpageId
	 * @param i
	 * @param number
	 * @return
	 */
	public List<CommodityDto> selectDynpageGoods(@Param("dynpageId") Integer dynpageId, @Param("start") Integer start,@Param("number") Integer number);
	
	/**
	 * 根据商品分类得到某范围中的商品列表
	 * @param goodsIds
	 * @param categoryIds
	 * @return
	 */
	public List<Long> selectCategoryGoods4Coupon(@Param("goodsIds")List<Long> goodsIds, @Param("categoryIds")List<Long> categoryIds);

	public long countDynpageGoodsByType(@Param("type") Integer type);

	public List<CommodityDto> selectDynpageGoodsByType(@Param("type") Integer type, @Param("start") Integer start,@Param("number") Integer number);
}
