package net.sls.dao.order;

import java.util.List;
import net.sls.dto.order.OrderPay;
import net.sls.dto.order.OrderPayExample;
import org.apache.ibatis.annotations.Param;

public interface OrderPayMapper {
    int countByExample(OrderPayExample example);

    int deleteByExample(OrderPayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    List<OrderPay> selectByExample(OrderPayExample example);

    OrderPay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderPay record, @Param("example") OrderPayExample example);

    int updateByExample(@Param("record") OrderPay record, @Param("example") OrderPayExample example);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);
}