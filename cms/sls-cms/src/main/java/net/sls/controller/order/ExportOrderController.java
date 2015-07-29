package net.sls.controller.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.businessconstant.BusinessContant;
import net.sls.service.order.IExportOrderService;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.exception.BusinessException;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("exportorder")
public class ExportOrderController {

	@InitBinder
	protected void parseDate(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false));
	}

	@RequestMapping("records.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String, Object>>>> exportOrderRecords(
			HttpServletRequest req) {
		IExportOrderService service = FindServiceUtil
				.findService(IExportOrderService.class);
		return service.selectExportOrderRecords(new ReqBo(req).setParam(
				BusinessContant.OPERAREAID,
				SessionUtil.get(BusinessContant.OPERAREAID)));
	}

	@RequestMapping("detail.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String, Object>>>> exportOrderDetail(
			HttpServletRequest req,
			@RequestParam(value = "exportId", required = false) Long exportId) {
		if (exportId == null) {
			return new ResBo<Pager<List<Map<String, Object>>>>();
		}
		IExportOrderService service = FindServiceUtil
				.findService(IExportOrderService.class);
		return service.selectExportOrderDetail(new ReqBo(req));
	}

	@RequestMapping("excel.htm")
	public void exportExcel(HttpServletResponse res,
			@RequestParam("exportId") long exportId,
			@RequestParam("exportNum") String exportNum,@RequestParam("type") int type) throws Exception {
		IExportOrderService service = FindServiceUtil
				.findService(IExportOrderService.class);
		ResBo<Workbook> resBo = service.exportExcel(new ReqBo().setParam(
				"exportId", exportId).setParam("type", type));
		if (resBo.isSuccess()) {
			res.setHeader("Content-Type", "application/force-download");
			res.setHeader("Content-Type",
					"application/vnd.ms-excel;charset=utf8");
			res.setHeader("Content-disposition", "attachment; filename="
					+ exportNum + ".xlsx");
			Workbook book = resBo.getResult();
			book.write(res.getOutputStream());
			res.getOutputStream().flush();
		} else {
			throw new BusinessException(18L);
		}
	}

	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<?> addExportOrderRecord(
			@RequestParam("startdate") Date startDate,
			@RequestParam("enddate") Date endDate) {
		IExportOrderService service = FindServiceUtil
				.findService(IExportOrderService.class);
		return service.insertExportOrderRecord(new ReqBo()
				.setParam("startDate", startDate)
				.setParam("endDate", endDate)
				.setParam(BusinessContant.OPERAREAID,
						SessionUtil.get(BusinessContant.OPERAREAID)));
	}

	@RequestMapping("deleterecord.htm")
	@ResponseBody
	public ResBo<?> deleteExportOrderRecord(
			@RequestParam("exportId") long exportId) {
		IExportOrderService service = FindServiceUtil
				.findService(IExportOrderService.class);
		return service.deleteExportOrderRecord(new ReqBo().setParam("exportId",
				exportId));
	}

	@RequestMapping("deletedetail.htm")
	@ResponseBody
	public ResBo<?> deleteExportOrderDetail(@RequestParam("id") long id,
			@RequestParam("exportNum") String exportNum) {
		IExportOrderService service = FindServiceUtil
				.findService(IExportOrderService.class);
		return service.deleteExportOrderDetail(new ReqBo().setParam("id", id)
				.setParam("exportNum", exportNum));
	}

	@RequestMapping("sendout.htm")
	@ResponseBody
	public ResBo<?> sendOut(@RequestParam("exportId") long exportId) {
		IExportOrderService service = FindServiceUtil
				.findService(IExportOrderService.class);
		return service.sendOut(new ReqBo().setParam("exportId", exportId));
	}
	
	@RequestMapping("select.htm")
	@ResponseBody
	public ResBo<?> select(@RequestParam("list") String ids,@RequestParam("curSelect") int select){
		IExportOrderService service = FindServiceUtil.findService(IExportOrderService.class);
		List<Long> list = new ArrayList<Long>();
		String[] sy = ids.split(",");
		for(String s:sy){
			list.add(Long.valueOf(s));
		}
		return service.changeSelect(new ReqBo().setParam("curSelect", select).setParam("ids", list));
	}

}
