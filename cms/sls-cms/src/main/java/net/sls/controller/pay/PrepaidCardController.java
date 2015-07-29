package net.sls.controller.pay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pay.PrepaidCard;
import net.sls.service.pay.IPrepaidCardService;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import framework.exception.BusinessException;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("ppc")

/**
 * 
 * @author sls006
 *
 */
public class PrepaidCardController {
	
	@InitBinder
	public void initBinder(HttpServletRequest request,ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * 
	 * @param parValue 
	 * @param start
	 * @param end
	 * @param remark
	 * @param count
	 * @return generated prepaid card collection.
	 */
	@RequestMapping("gen.htm")
	@ResponseBody
	public ResBo<?> genCard(@ModelAttribute PrepaidCard pc,@RequestParam("count") int count ){		
		ReqBo rb = new ReqBo();
		rb.setParam("prepaidcard", pc);
		rb.setParam("count", count);		
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.insertPrepaidCard(rb);
	}
	
	@RequestMapping("getGen.htm")
	public String getGen(){
		return "pay/gen";
	}
	
	@RequestMapping("getChk.htm")
	public String getChk(){
		return "pay/chk";
	}
	
	@RequestMapping("getQry.htm")
	public String getQry(){
		return "pay/qry";
	}
	
	/**
	 * 
	 * @param rc
	 * @param pageIdx
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("qry.htm")
	@ResponseBody
	public ResBo<Pager<List<PrepaidCard>>> query(@ModelAttribute PrepaidCard rc, @RequestParam("idStart")Integer idStart,@RequestParam("idEnd") Integer idEnd,  @RequestParam("page") int pageIdx,@RequestParam("rows")int pageSize){
		ReqBo rb = new ReqBo();
		rb.setReqModel(rc);		
		rb.setParam("pageIdx", pageIdx);
		rb.setParam("IdStart", idStart);
		rb.setParam("IdEnd", idEnd);
		rb.setParam("pageSize", pageSize);
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.selectPrepaidCard(rb);
	}
	
	/**
	 * 
	 * @param rc 
	 * @param pageIdx
	 * @param pageSize
	 * @return Pager<List<PrepaidCare>> object , 
	 * 			Note:total pager is group by batch
	 */
	@RequestMapping("qryBatch.htm")
	@ResponseBody
	public ResBo<Pager<List<PrepaidCard>>> queryBatch(@ModelAttribute PrepaidCard rc,  @RequestParam("page") int pageIdx,@RequestParam("rows")int pageSize){
		ReqBo rb = new ReqBo();
		rb.setReqModel(rc);		
		rb.setParam("pageIdx", pageIdx);
		rb.setParam("pageSize", pageSize);
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.selectBatch(rb);
	}
	

	/**
	 * Check status :
	 * 
	 * case 1:return "未审核";
	   case 2:return "审核中";
	   case 3:return "已审核";	
	 * 
	 * @param batch
	 * @param chkStatus
	 * @return
	 */
	@RequestMapping("chkBatch.htm")
	@ResponseBody
	public ResBo<?> setChkStatus( @RequestParam("batch") String batch){
		ReqBo rb = new ReqBo();
		rb.setParam("batch", batch);
		rb.setParam("chkStatus", (byte)3);
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.updatePrepaidCardCheckStatus(rb);
	}
	/**
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("chkByIds.htm")
	@ResponseBody
	public ResBo<?>setChkStatusByIds(@RequestParam("ids") String ids){
		String[] ss = ids.split(",");
		int len = ss.length;
		Long[] IDs = new Long [len];
		for(int i = 0;i<len;i++){
			IDs[i] = Long.parseLong(ss[i]);
		}
		ReqBo rb = new ReqBo();		
		rb.setParam("ids", IDs);
		rb.setParam("chkStatus", (byte)3);// 
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.updatePrepaidCardCheckStatus(rb);
	}
	
	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("setCardStatus.htm")
	@ResponseBody
	public ResBo<?> setCardStatus( @RequestParam("id") long id,@RequestParam("status") byte status){
		ReqBo rb = new ReqBo();
		rb.setParam("id", id);
		rb.setParam("status", status);
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.updatePrepaidCardStatus(rb);
	}
	
	/**
	 * 
	 * @param res
	 * @param batch
	 * @param exportNum
	 * @param type
	 * @throws Exception
	 */
	@RequestMapping("exportxls.htm")
	public void exportXls(HttpServletResponse res,
			@RequestParam("batch") String batch,
			@RequestParam("numStart") String numStart,@RequestParam("numEnd") String numEnd) throws Exception {
		IPrepaidCardService service = FindServiceUtil.findService(IPrepaidCardService.class);
		
		@SuppressWarnings("unchecked")
		ResBo<Workbook> resBo = (ResBo<Workbook>) service.exportExcel(
				new ReqBo()
				.setParam("batch",batch)
				.setParam("numStart", numStart)
				.setParam("numEnd", numEnd)
				);
		if (resBo.isSuccess()) {			
			res.setHeader("Content-Type", "application/force-download");
			res.setHeader("Content-Type",
					"application/vnd.ms-excel;charset=utf8");
			res.setHeader("Content-disposition", "attachment; filename="
					+ "Prepaid_Card" + ".xls");
			Workbook book = resBo.getResult();
			book.write(res.getOutputStream());
			res.getOutputStream().flush();
		} else {
			throw new BusinessException(18L);
		}
	}	
	
	/**
	 * 
	 * @param cardNum
	 * @param status
	 * @return rows effort
	 */
	@RequestMapping("chstus.htm")
	public ResBo<?> updatePrepaidCardStatus(String cardNum,byte status){
		ReqBo rb = new ReqBo();
		rb.setParam("cardNum", cardNum);
		rb.setParam("status", status);
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.updatePrepaidCardStatus(rb);
	}
	
	/**
	 * 
	 * @param batch
	 * @param status
	 * @return rows effort
	 */
	@RequestMapping("chchk.htm")
	public ResBo<?> updatePrepaidCardCheckStatus(String batch,byte status){
		ReqBo rb = new ReqBo();
		rb.setParam("batch", batch);
		rb.setParam("chkStatus", status);
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.updatePrepaidCardCheckStatus(rb);
	}	
	

	@RequestMapping("getNextBatchNum.htm")
	@ResponseBody
	public ResBo<?> getNextBatchNum(){			
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.getNextBatchNum();
	}
	
	@RequestMapping("statistics.htm")
	public String initStatistics(Model model){
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		model.addAttribute("re", pcs.statistics().getResult());
		return "pay/statistics";
	}
	
	@RequestMapping("rechargedInfo.htm")
	@ResponseBody
	public ResBo<?> rechargedInfo(HttpServletRequest req){
		IPrepaidCardService pcs = FindServiceUtil.findService(IPrepaidCardService.class);
		return pcs.rechargedInfo(new ReqBo(req));
	}
	
}
