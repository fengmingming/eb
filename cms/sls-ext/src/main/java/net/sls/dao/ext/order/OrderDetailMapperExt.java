package net.sls.dao.ext.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderDetailMapperExt {

	long countOrderDetailsByFilter(@Param("orderId") Integer orderId);

	List<Map<String, Object>> selectOrderDetailList(@Param("orderId") Integer orderId,@Param("start") Integer start,@Param("number") Integer number);

}
