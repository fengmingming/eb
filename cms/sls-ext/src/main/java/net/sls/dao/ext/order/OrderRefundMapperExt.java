package net.sls.dao.ext.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderRefundMapperExt {

	public List<Map<String, Object>> refunds(
			@Param("orderNum") String orderNum, @Param("state") Integer state,
			@Param("type") Integer type, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("start") int start,
			@Param("number") int number);

	public Long countRefunds(@Param("orderNum") String orderNum,
			@Param("state") Integer state, @Param("type") Integer type,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public List<Map<String, Object>> refundExcel(
			@Param("orderNum") String orderNum, @Param("state") Integer state,
			@Param("type") Integer type, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	public long isExistRefund(long orderId);
	
	public long hdExistRefund(long orderId);
	
}
