package net.sls.component.pc.product;


import java.util.List;

import net.sls.dto.pc.product.CommodityDto;
import net.sls.dto.pc.shopcart.GoodDto;
import framework.web.Pager;

public interface IGoodsComponent {

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
	 * @param sortSale 销量的排序类型：0--从高到低，1--从低到高，null不排序
	 * @param page 页数
	 * @param rows 每页显示的记录数据
	 * @return Pager<List<Map<Object,Object>>>
	 */
	public Pager<List<CommodityDto>> selectGoods(Long first, Long second, Long third,List<Integer> areaIds,Integer actType, Integer hasGoods,Double minPrice,Double maxPrice,Integer sortPrice,
		      Integer sortNew,Integer sortSale,Integer start, Integer number);
	
	/**@author wangshaohui
	 * @Description: TODO 查询商品详情，根据商品id。查到的内容包括：四张小图、一张大图、商品描述、虚拟库存
	 * @param id id是商品id
	 * @return CommodityDto
	 */
	public CommodityDto selectGoods(long id,Integer provinceId,Integer cityId,Integer districtId,Integer communityId,Integer pavilionId);
	/**
	 * 查询商品列表根据ids
	 * @param goodIdList
	 * @return 
	 */
	public List<CommodityDto> selectGoodsCartDto(List<Long> goodIdList);
	/**
	 * 查询商品是否限购
	 * @param goodId
	 * @return
	 */
	public GoodDto selectGoodDto(Long goodId);
	
	/**@author wangshaohui
	 * @Description: TODO 查询产品包括详情，根据品类id，查出它的记录条数
	 * @Param contents 输入的内容
	 * @Param areaIds 区域范围ids
	 * @Param hasGoods 是否仅显示有货，1表示仅显示有货
	 * @param minPrice 最低价钱
	 * @param maxPrice 最高价钱
	 * @param sortPrice 价钱的排序类型：0--从小到大，1--从大到小 ，null不排序
	 * @param sortNew 新品的排序类型：0--从新到旧，1--从旧到新，null不排序
	 * @param sortSale 销量的排序类型：0--从高到低，1--从低到高，null不排序
	 * @param page 页数
	 * @param rows 每页显示的记录数据
	 * @return Pager<List<Map<Object,Object>>>
	 */
	public Pager<List<CommodityDto>> selectGoodsList(String contents[],List<Integer> areaIds,Integer hasGoods,Double minPrice,Double maxPrice,Integer sortPrice,
		      Integer sortNew,Integer sortSale,Integer start, Integer number);

	/**
	 * 根据商品Ids得到商品信息，并按照id的顺序排序（lucene搜索后使用）
	 * @param goodsIds
	 * @return
	 */
	Pager<List<CommodityDto>> selectGoodsByIds(List<Long> goodsIds);
	
	/**
	 * 市场活动
	 * @param dynpageId
	 * @param start
	 * @param number
	 * @return
	 */
	public Pager<List<CommodityDto>> selectDynpageGoodsPageList(
			Integer dynpageId, Integer start,
			Integer number);

	/**
	 * 根据商品分类得到某范围中的商品列表
	 * @param goodsIds
	 * @param categoryIds
	 * @return
	 */
	List<Long> selectCategoryGoods4Coupon(List<Long> goodsIds, List<Long> categoryIds);
	/**
	 * 商品专场
	 * @param type
	 * @param start
	 * @param number
	 * @return
	 */
	public Pager<List<CommodityDto>> selectDynpageGoodsListByType(
			Integer type, Integer start, Integer number);
}
