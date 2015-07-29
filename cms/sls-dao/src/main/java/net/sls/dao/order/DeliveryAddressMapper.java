package net.sls.dao.order;

import java.util.List;
import net.sls.dto.order.DeliveryAddress;
import net.sls.dto.order.DeliveryAddressExample;
import org.apache.ibatis.annotations.Param;

public interface DeliveryAddressMapper {
    int countByExample(DeliveryAddressExample example);

    int deleteByExample(DeliveryAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeliveryAddress record);

    int insertSelective(DeliveryAddress record);

    List<DeliveryAddress> selectByExample(DeliveryAddressExample example);

    DeliveryAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeliveryAddress record, @Param("example") DeliveryAddressExample example);

    int updateByExample(@Param("record") DeliveryAddress record, @Param("example") DeliveryAddressExample example);

    int updateByPrimaryKeySelective(DeliveryAddress record);

    int updateByPrimaryKey(DeliveryAddress record);
}