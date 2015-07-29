package net.sls.component.pc.product;

import java.util.List;

import net.sls.dto.pc.order.OrderDetail;
import net.sls.dto.pc.product.GoodsStock;

public interface IGoodsStockComponent {

	public GoodsStock selectGoodsStock(Long goodId);
	
	/**@author wangshaohui
	 * @Description: TODO 根据商品id修改库存数量
	 * @param goodsId void
	 * @param num 修改的数量
	 * @return int 返回修改的记录数
	 * @date 2015年1月6日 下午4:46:58
	 */
	public int updateGoodsStock(Long goodsId,Integer num);
	
	/**
	 * 取消订单时候修改库存
	 * @param goodsId
	 * @param num
	 * @return
	 */
	public int updateGoodsStockAdd(Long goodsId, Integer num);

	/**
	 * 取消订单时候修改库存
	 * @param goodsId
	 * @param num
	 * @return
	 */

	public int updateGoodsStockAdd(List<OrderDetail> orderDetailList);
}
