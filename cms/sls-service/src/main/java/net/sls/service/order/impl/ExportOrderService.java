package net.sls.service.order.impl;

import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.order.IExportOrderComponent;
import net.sls.dto.ext.order.ExportOrderCG;
import net.sls.dto.ext.order.ExportOrderFH;
import net.sls.dto.user.CmsUser;
import net.sls.service.order.IExportOrderService;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class ExportOrderService implements IExportOrderService {

	@Autowired
	private IExportOrderComponent component;

	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectExportOrderRecords(
			ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String, Object>>>>(
				component.selectExportOrderRecords(
						reqBo.getParamStr(BusinessContant.OPERAREAID),
						reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectExportOrderDetail(
			ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String, Object>>>>(
				component.selectExportOrderDetail(
						reqBo.getParamStr("orderNum"),
						reqBo.getParamInt("isSelect"),
						reqBo.getParamLong("goodsId"),
						reqBo.getParamStr("goodsName"),
						reqBo.getParamStr("sku"),
						reqBo.getParamInt("providerId"),
						reqBo.getParamLong("exportId"),
						reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<?> insertExportOrderRecord(ReqBo reqBo) {
		component.insertExportOrderRecord(((CmsUser) SessionUtil
				.get(BusinessContant.CMSUSER)).getId(), reqBo
				.getParamDate("startDate"), reqBo.getParamDate("endDate"),reqBo.getParamStr(BusinessContant.OPERAREAID));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> deleteExportOrderRecord(ReqBo reqBo) {
		boolean b = component.deleteExportOrderRecord(reqBo
				.getParamLong("exportId"));
		if (b) {
			return new ResBo<Object>();
		}
		return new ResBo<Object>(3L);
	}

	@Override
	public ResBo<?> deleteExportOrderDetail(ReqBo reqBo) {
		boolean b = component.deleteExportOrderDetail(reqBo.getParamLong("id"),
				reqBo.getParamStr("orderNum"));
		if (b) {
			return new ResBo<Object>();
		}
		return new ResBo<Object>(3L);
	}

	@Override
	public ResBo<Workbook> exportExcel(ReqBo reqBo) {
		long exportId = reqBo.getParamLong("exportId");
		int type = reqBo.getParamInt("type");//1采购单2发货单
		Workbook book = new XSSFWorkbook();
		Sheet sheet = book.createSheet();
		if(type == 1){
			List<ExportOrderCG> list = component.selectExportOrderCG(exportId);
			int i = 0;
			Row row = sheet.createRow(i);
			row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("品牌id");
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("品牌名称");
			row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("商品名称");
			row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("供货商名称");
			row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("数量");
			for(ExportOrderCG cg:list){
				i = i + 1;
				row = sheet.createRow(i);
				if(cg.getBrandName() != null){
					row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(cg.getBrandId());
					row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(cg.getBrandName());
				}
				row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(cg.getGoodsName());
				row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(cg.getProviderName());
				row.createCell(4, Cell.CELL_TYPE_NUMERIC).setCellValue(cg.getNumber());
			}
		}else if(type == 2){
			List<ExportOrderFH> list = component.selectExportOrderFH(exportId);
			int i = 0;
			Row row = sheet.createRow(i);
			row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("亭子");
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("货物");
			row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("数量");
			for(ExportOrderFH fh:list){
				i = i + 1;
				row = sheet.createRow(i);
				row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(fh.getPavilionName());
				row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(fh.getGoodsName());
				row.createCell(2, Cell.CELL_TYPE_NUMERIC).setCellValue(fh.getNumber());
			}
		}
		component.updateExportOrderStatus(exportId);
		return new ResBo<Workbook>(book);
	}

	@Override
	public ResBo<?> sendOut(ReqBo reqBo) {
		component.updateSendOut(reqBo.getParamLong("exportId"));
		return new ResBo<Object>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResBo<?> changeSelect(ReqBo reqBo) {
		component.updateChangeSelect((List<Long>) reqBo.getParam("ids"), reqBo.getParamInt("curSelect"));
		return new ResBo<Object>();
	}

}
