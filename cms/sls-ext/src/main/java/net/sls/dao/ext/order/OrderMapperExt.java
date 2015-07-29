package net.sls.dao.ext.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.dto.ext.order.ExportOrderQueryDto;
import net.sls.dto.order.Orders;

import org.apache.ibatis.annotations.Param;

import util.model.ComboxDto;

public interface OrderMapperExt {

	public List<Map<Object, Object>> selectOrdersByUserFilter(
			@Param("areaCode") String areaCode,
			@Param("userName") String userName, @Param("mobile") String mobile,
			@Param("orderNum") String orderNum,
			@Param("isPaid") Integer isPaid, @Param("state") Integer state,
			@Param("status") Integer status,
			@Param("pavilionId") Integer pavilionId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("isPaviOrder") Integer isPaviOrder,
			@Param("isMobile") Integer isMobile, @Param("type") Integer type,
			@Param("start") Integer start, @Param("number") Integer number);

	public long countOrdersByUserFilter(@Param("areaCode") String areaCode,
			@Param("userName") String userName, @Param("mobile") String mobile,
			@Param("orderNum") String orderNum,
			@Param("isPaid") Integer isPaid, @Param("state") Integer state,
			@Param("status") Integer status,
			@Param("pavilionId") Integer pavilionId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("isPaviOrder") Integer isPaviOrder,
			@Param("isMobile") Integer isMobile, @Param("type") Integer type);

	public List<ExportOrderQueryDto> selectExportExcel(
			@Param("areaCode") String areaCode,
			@Param("userName") String userName, @Param("mobile") String mobile,
			@Param("orderNum") String orderNum,
			@Param("isPaid") Integer isPaid, @Param("state") Integer state,
			@Param("status") Integer status,
			@Param("pavilionId") Integer pavilionId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("isPaviOrder") Integer isPaviOrder,
			@Param("isMobile") Integer isMobile, @Param("type") Integer type,
			@Param("hasDetail") Integer hasDetail);

	public List<ComboxDto> selectPavilionComboxDto(@Param("name") String name,
			@Param("areaCode") String areaCode);

	int updateOrderPaid(long orderId);

	int updateOrderStateOrStatus(Orders order);
	
	Map<String,Object> selectOrderCoupon(long orderId);
	
	Map<String,Object> orderInfoByOrderNum(String orderNum);
	
	int insertOrders(Orders orders);
}
