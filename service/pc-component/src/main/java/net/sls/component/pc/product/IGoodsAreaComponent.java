package net.sls.component.pc.product;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.product.GoodsArea;
import net.sls.dto.pc.product.OrderStockDto;
import net.sls.dto.pc.product.SmallGoodsArea;

public interface IGoodsAreaComponent {
	/**
	 * 
	 * @param goodId
	 * @param provinceId
	 * @param cityId
	 * @param districtId
	 * @param communityId
	 * @param pavilionId
	 * @return GoodsArea
	 */
	public GoodsArea selectGoodsAreaByFilter(Long goodId, Integer provinceId,
			Integer cityId, Integer districtId, Integer communityId, Integer pavilionId);

	/**
	 * @author wangshaohui
	 * @Description: TODO 根据商品id修改限购数量
	 * @param goodsId
	 *            void
	 * @param num
	 *            修改的数量
	 * @return int 返回修改的记录数
	 * @date 2015年1月6日 下午4:46:58
	 */
	public int updateGoodsArea(Long goodsId, Integer num);
	
	public int updateGoodsAreas(List<OrderStockDto> list);

	public List<SmallGoodsArea> selectSmallGoodsAreas(Map<String,Object> map);

}
