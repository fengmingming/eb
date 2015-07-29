package net.sls.dao.order;

import java.util.List;
import net.sls.dto.order.OrderLog;
import net.sls.dto.order.OrderLogExample;
import org.apache.ibatis.annotations.Param;

public interface OrderLogMapper {
    int countByExample(OrderLogExample example);

    int deleteByExample(OrderLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderLog record);

    int insertSelective(OrderLog record);

    List<OrderLog> selectByExample(OrderLogExample example);

    OrderLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderLog record, @Param("example") OrderLogExample example);

    int updateByExample(@Param("record") OrderLog record, @Param("example") OrderLogExample example);

    int updateByPrimaryKeySelective(OrderLog record);

    int updateByPrimaryKey(OrderLog record);
}