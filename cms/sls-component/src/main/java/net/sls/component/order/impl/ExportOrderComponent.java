package net.sls.component.order.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.model.OrderStatusEnum;
import net.sls.component.order.IExportOrderComponent;
import net.sls.dao.ext.order.ExportOrderDetailMapperExt;
import net.sls.dao.order.ExportOrderDetailMapper;
import net.sls.dao.order.ExportOrderRecordMapper;
import net.sls.dao.order.OrdersMapper;
import net.sls.dto.ext.order.ExportDto;
import net.sls.dto.ext.order.ExportOrderCG;
import net.sls.dto.ext.order.ExportOrderFH;
import net.sls.dto.order.ExportOrderDetail;
import net.sls.dto.order.ExportOrderDetailExample;
import net.sls.dto.order.ExportOrderRecord;
import net.sls.dto.order.ExportOrderRecordExample;
import net.sls.dto.order.Orders;
import net.sls.dto.order.OrdersExample;
import net.sls.dto.product.Provider;
import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class ExportOrderComponent implements IExportOrderComponent{
	
	@Autowired
	private ExportOrderDetailMapperExt ext;
	
	@Autowired
	private ExportOrderDetailMapper eodMapper;
	
	@Autowired
	private ExportOrderRecordMapper mapper;
	
	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public Pager<List<Map<String,Object>>> selectExportOrderRecords(
			String areaCode, int start, int number) {
		return new Pager<List<Map<String,Object>>>(ext.selectExportOrderRecords(areaCode, (start-1)*number, number),ext.countExportOrderRecords(areaCode));
	}

	@Override
	public Pager<List<Map<String,Object>>> selectExportOrderDetail(String orderNum,Integer isSelect,Long goodsId,String goodsName,String sku,Integer providerId,
			long exportId, int start, int number) {
		return new Pager<List<Map<String,Object>>>(ext.selectExportOrderDetail(orderNum,isSelect,goodsId,goodsName,sku,providerId,exportId, (start-1)*number, number), ext.countExportOrderDetail(orderNum,isSelect,goodsId,goodsName,sku,providerId,exportId));
	}

	@Override
	public void insertExportOrderRecord(long userId,Date startDate, Date endDate,String areaCode) {
		ExportOrderRecord record = new ExportOrderRecord();
		record.setExportNum(createExportNum(areaCode,startDate,endDate));
		record.setCreateTime(new Date());
		record.setEnddate(endDate);
		record.setStartdate(startDate);
		record.setOperId(userId);
		record.setState(0);
		int i = ext.insertExportOrderRecord(record);
		if(i == 1){
			ext.updateOrderStatusBeforeInsertExportOrderDetails(startDate, endDate);
			ext.insertExportOrderDetails(record.getId(), startDate, endDate);
		}else{
			throw new BusinessException(1L);
		}
	}
	
	private String createExportNum(String areaCode,Date startDate, Date endDate){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String exportNum = areaCode+"$"+format.format(startDate)+format.format(endDate)+((int)Math.floor(Math.random()*100));
		return exportNum;
	}

	@Override
	public boolean selectIsExistNotExportOrderRecord() {
		ExportOrderRecordExample e = new ExportOrderRecordExample();
		e.createCriteria().andStateEqualTo(0).andIsDelNotEqualTo(127);
		int i = mapper.countByExample(e);
		if(i > 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteExportOrderRecord(long exportId) {
		ExportOrderRecord record = new ExportOrderRecord();
		record.setIsDel(127);
		ExportOrderRecordExample e = new ExportOrderRecordExample();
		e.createCriteria().andIdEqualTo(exportId).andStateNotEqualTo(2);
		int i = mapper.updateByExampleSelective(record, e);
		if(i == 1){
			ext.deleteExportOrderAfter(exportId);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteExportOrderDetail(long id, String exportNum) {
		int i = ext.deleteExportOrderDetatil(id, exportNum);
		if(i == 1){
			ExportOrderDetail detail = eodMapper.selectByPrimaryKey(id);
			Orders orders = new Orders();
			orders.setStatus(OrderStatusEnum.status_2.getStatus());
			OrdersExample e = new OrdersExample();
			e.createCriteria().andIdEqualTo(detail.getOrderId()).andStateEqualTo(3);
			i = ordersMapper.updateByExampleSelective(orders, e);
			if(i != 1){
				throw new BusinessException(2L);
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Provider> selectExportProviders(long exportId) {
		return ext.selectExportProviders(exportId);
	}

	@Override
	public List<ExportDto> selectExportExcel(long exportId, long providerId) {
		return ext.selectExportExcel(exportId, providerId);
	}

	@Override
	public void updateExportOrderStatus(long exportId) {
		ext.updateExportOrderRecordNumber(exportId);
	}

	@Override
	public void updateSendOut(long exportId) {
		ExportOrderRecord record = mapper.selectByPrimaryKey(exportId);
		if(record.getState() != 2){
			ext.updateOrderStatusAfterExport(exportId);
			ExportOrderDetailExample e = new ExportOrderDetailExample();
			e.createCriteria().andIsSelectEqualTo(0).andExportIdEqualTo(exportId);
			int count = eodMapper.countByExample(e);
			if(count == 0){
				ext.updateExportOrderRecordOk(exportId);
			}
		}else{
			throw new BusinessException(26L);
		}
	}

	@Override
	public void updateChangeSelect(List<Long> ids, int type) {
		ext.changeSelect(ids, type);
	}

	@Override
	public List<ExportOrderFH> selectExportOrderFH(long exportId) {
		return ext.selectExportOrderFH(exportId);
	}

	@Override
	public List<ExportOrderCG> selectExportOrderCG(long exportId) {
		return ext.selectExportOrderCG(exportId);
	}
}
