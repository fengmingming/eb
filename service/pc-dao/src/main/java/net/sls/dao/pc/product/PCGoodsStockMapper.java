package net.sls.dao.pc.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.pc.order.OrderDetail;
import net.sls.dto.pc.product.GoodsStock;

public interface PCGoodsStockMapper {

	public GoodsStock selectGoodsStock(@Param("goodsId") Long goodsId);

	/**@author wangshaohui
	 * @Description: TODO 根据商品id修改库存数量
	 * @param goodsId void
	 * @param num 修改的数量
	 * @return int 返回修改的记录数，没修改返回0
	 * @date 2015年1月6日 下午4:46:58
	 */
	public int updateGoodsStock(@Param("goodsId") Long goodsId, @Param("num") Integer num);
	/**
	 * 取消订单修改库存
	 * @param goodsId
	 * @param num
	 * @return
	 */
	public int updateGoodsStockAdd(@Param("goodsId") Long goodsId, @Param("num") Integer num);

	public int updateGoodsStockAdd(@Param("orderDetailList") List<OrderDetail> orderDetailList);

}
