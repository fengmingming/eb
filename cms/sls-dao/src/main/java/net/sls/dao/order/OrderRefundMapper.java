package net.sls.dao.order;

import java.util.List;
import net.sls.dto.order.OrderRefund;
import net.sls.dto.order.OrderRefundExample;
import org.apache.ibatis.annotations.Param;

public interface OrderRefundMapper {
    int countByExample(OrderRefundExample example);

    int deleteByExample(OrderRefundExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    List<OrderRefund> selectByExample(OrderRefundExample example);

    OrderRefund selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderRefund record, @Param("example") OrderRefundExample example);

    int updateByExample(@Param("record") OrderRefund record, @Param("example") OrderRefundExample example);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);
}