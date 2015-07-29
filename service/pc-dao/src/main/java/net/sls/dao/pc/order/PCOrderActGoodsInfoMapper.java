package net.sls.dao.pc.order;

import java.util.List;

import net.sls.dto.pc.order.OrderActGoodsInfo;

import org.apache.ibatis.annotations.Param;

public interface PCOrderActGoodsInfoMapper {

	int selectNumber(@Param("actId") Long actId,@Param("goodsId") Long goodsId);
	
	List<OrderActGoodsInfo> selectNumberByUserId(@Param("userId") Long userId, @Param("goodsId") List<Long> goodsIds, @Param("actIds") List<Long> actIds);

	int insert(OrderActGoodsInfo oag);
}
