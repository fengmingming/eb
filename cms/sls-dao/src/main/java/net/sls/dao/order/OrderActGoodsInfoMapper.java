package net.sls.dao.order;

import java.util.List;
import net.sls.dto.order.OrderActGoodsInfo;
import net.sls.dto.order.OrderActGoodsInfoExample;
import org.apache.ibatis.annotations.Param;

public interface OrderActGoodsInfoMapper {
    int countByExample(OrderActGoodsInfoExample example);

    int deleteByExample(OrderActGoodsInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderActGoodsInfo record);

    int insertSelective(OrderActGoodsInfo record);

    List<OrderActGoodsInfo> selectByExample(OrderActGoodsInfoExample example);

    OrderActGoodsInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderActGoodsInfo record, @Param("example") OrderActGoodsInfoExample example);

    int updateByExample(@Param("record") OrderActGoodsInfo record, @Param("example") OrderActGoodsInfoExample example);

    int updateByPrimaryKeySelective(OrderActGoodsInfo record);

    int updateByPrimaryKey(OrderActGoodsInfo record);
}