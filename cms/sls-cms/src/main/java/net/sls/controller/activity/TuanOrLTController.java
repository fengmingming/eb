package net.sls.controller.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.activity.Activities;
import net.sls.dto.activity.ActivityGoods;
import net.sls.dto.user.CmsUser;
import net.sls.service.activity.ITuanOrLTService;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("tuan")
public class TuanOrLTController {
	
	private ObjectMapper om = new ObjectMapper();
	
	@InitBinder
	public void initBinder(HttpServletRequest request,ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	@RequestMapping("add_page.htm")
	public String add_page(){
		return "tuan/add_tuan";
	}
	
	@RequestMapping("sel_page.htm")
	public String sel_page(){
		return "tuan/sel_tuan";
	}
	
	@RequestMapping("approval_page.htm")
	public String approval_page(){
		return "tuan/approval_tuan";
	}
	
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<?> add(HttpServletRequest req,@ModelAttribute Activities act) throws Exception{
		String data = req.getParameter("data");
		String areaCode = (String) SessionUtil.get(BusinessContant.OPERAREAID);
		act.setAreaCode(areaCode);
		act.setCreateTime(new Date());
		CmsUser cu = (CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
		act.setCreateUserId(cu.getId());
		List<ActivityGoods> list = om.readValue(data, new TypeReference<List<ActivityGoods>>() {});
		ITuanOrLTService service = FindServiceUtil.findService(ITuanOrLTService.class);
		return service.addTuanOrLT(new ReqBo().setParam("act", act).setParam("list", list));
	}
	
	@RequestMapping("updateDetail.htm")
	@ResponseBody
	public ResBo<?> updateDetail(@ModelAttribute ActivityGoods ag){
		ITuanOrLTService service = FindServiceUtil.findService(ITuanOrLTService.class);
		return service.updateTuanOrLTDetail(new ReqBo().setReqModel(ag));
	}
	
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<?> update(@ModelAttribute Activities act){
		ITuanOrLTService service = FindServiceUtil.findService(ITuanOrLTService.class);
		return service.updateTuanOrLT(new ReqBo().setReqModel(act));
	}
	
	@RequestMapping("sel.htm")
	@ResponseBody
	public ResBo<Pager<List<Activities>>> selTuanOrLT(HttpServletRequest req){
		ITuanOrLTService service = FindServiceUtil.findService(ITuanOrLTService.class);
		return service.selTuanOrLT(new ReqBo(req).setParam(BusinessContant.OPERAREAID, SessionUtil.get(BusinessContant.OPERAREAID)));
	}
	
	@RequestMapping("detail.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> selTuanOrLTDetail(HttpServletRequest req){
		if(req.getParameter("actId") == null){
			return new ResBo<Pager<List<Map<String,Object>>>>();
		}
		ITuanOrLTService service = FindServiceUtil.findService(ITuanOrLTService.class);
		return service.selTuanOrLTDetail(new ReqBo(req));
	}
	
	
}
