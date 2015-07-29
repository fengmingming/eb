package net.sls.dao.ext.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.dto.ext.order.ExportDto;
import net.sls.dto.ext.order.ExportOrderCG;
import net.sls.dto.ext.order.ExportOrderFH;
import net.sls.dto.order.ExportOrderDetail;
import net.sls.dto.order.ExportOrderRecord;
import net.sls.dto.product.Provider;

import org.apache.ibatis.annotations.Param;

public interface ExportOrderDetailMapperExt {

	public int insertExportOrderDetail(ExportOrderDetail eod);

	public List<Map<String, Object>> selectExportOrderRecords(
			@Param("areaCode") String areaCode, @Param("start") int start,
			@Param("number") int number);

	public long countExportOrderRecords(String areaCode);

	public List<Map<String, Object>> selectExportOrderDetail(
			@Param("orderNum") String orderNum, @Param("isSelect") Integer isSelect,
			@Param("goodsId") Long goodsId,
			@Param("goodsName") String goodsName, @Param("sku") String sku,
			@Param("providerId") Integer providerId,
			@Param("exportId") long exportId, @Param("start") int start,
			@Param("number") int number);

	public long countExportOrderDetail(@Param("orderNum") String orderNum,
			@Param("isSelect") Integer isSelect, @Param("goodsId") Long goodsId,
			@Param("goodsName") String goodsName, @Param("sku") String sku,
			@Param("providerId") Integer providerId,
			@Param("exportId") long exportId);

	public int insertExportOrderRecord(ExportOrderRecord record);

	public int updateOrderStatusBeforeInsertExportOrderDetails(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	public int insertExportOrderDetails(@Param("exportId") Long exportId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	public int deleteExportOrderDetatil(@Param("id") Long id,
			@Param("exportNum") String exportNum);

	public List<Provider> selectExportProviders(Long exportId);

	public List<ExportDto> selectExportExcel(@Param("exportId") Long exportId,
			@Param("providerId") Long providerId);
	
	public void updateOrderStatusAfterExport(Long exportId);

	public int updateExportOrderRecordNumber(Long exportId);
	
	public int updateExportOrderRecordOk(Long exportId);
	
	public int deleteExportOrderAfter(Long exportId);
	
	public int changeSelect(@Param("list")List<Long> ids,@Param("type")int type);
	
	public List<ExportOrderFH> selectExportOrderFH(long exportId);
	
	public List<ExportOrderCG> selectExportOrderCG(long exportId);

}