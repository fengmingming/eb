package net.sls.service.order;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IExportOrderService {

	/**
	 * 
	 * 查询订单导出记录
	 * 
	 * */
	public ResBo<Pager<List<Map<String,Object>>>> selectExportOrderRecords(ReqBo reqBo);
	
	/**
	 * 
	 * 查询订单导出详情
	 * 
	 * */
	public ResBo<Pager<List<Map<String,Object>>>> selectExportOrderDetail(ReqBo reqBo);
	
	/**
	 * 
	 * 新增订单导出记录
	 * 
	 * */
	public ResBo<?> insertExportOrderRecord(ReqBo reqBo);
	
	public ResBo<?> deleteExportOrderRecord(ReqBo reqBo);
	
	public ResBo<?> deleteExportOrderDetail(ReqBo reqBo);
	
	public ResBo<Workbook> exportExcel(ReqBo reqBo);
	
	public ResBo<?> sendOut(ReqBo reqBo);
	
	public ResBo<?> changeSelect(ReqBo reqBo);
	
}
