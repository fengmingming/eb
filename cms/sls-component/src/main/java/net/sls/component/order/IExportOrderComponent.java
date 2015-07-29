package net.sls.component.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.dto.ext.order.ExportDto;
import net.sls.dto.ext.order.ExportOrderCG;
import net.sls.dto.ext.order.ExportOrderFH;
import net.sls.dto.product.Provider;
import framework.web.Pager;

public interface IExportOrderComponent {

	public Pager<List<Map<String,Object>>> selectExportOrderRecords(String areaCode, int start, int number);
	
	public Pager<List<Map<String,Object>>> selectExportOrderDetail(String orderNum,Integer isSelect,Long goodsId,String goodsName,String sku,Integer providerId,long exportId, int start, int number);
	
	public void insertExportOrderRecord(long userId,Date startDate,Date endDate,String areaCode);
	
	public boolean selectIsExistNotExportOrderRecord();
	
	public boolean deleteExportOrderRecord(long exportId);
	
	public boolean deleteExportOrderDetail(long id,String exportNum);
	
	/**
	 * 
	 * 查询导出记录中存在多少供货商
	 * 
	 * */
	public List<Provider> selectExportProviders(long exportId);
	
	/**
	 * 
	 * 根据供货商id，导出记录id查询导出商品
	 * 
	 * */
	public List<ExportDto> selectExportExcel(long exportId,long providerId);
	
	/**
	 * 
	 * 导出订单后修改订单状态
	 * */
	public void updateExportOrderStatus(long exportId);
	
	public void updateSendOut(long exportId);
	
	public void updateChangeSelect(List<Long> ids,int type);
	
	/**
	 * 导出发货单
	 * */
	public List<ExportOrderFH> selectExportOrderFH(long exportId);
	
	/**
	 * 导出发采购
	 * */
	public List<ExportOrderCG> selectExportOrderCG(long exportId);
}
