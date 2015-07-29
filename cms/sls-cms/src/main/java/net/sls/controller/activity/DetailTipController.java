package net.sls.controller.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.activity.DetailTip;
import net.sls.service.activity.IDetailTipService;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("detailtip")
public class DetailTipController {
	
	@InitBinder
	public void initBinder(HttpServletRequest request,ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

	@RequestMapping("init.htm")
	public String detail(){
		return "activity/detailtip";
	}
	
	@RequestMapping("sel.htm")
	public String sel(){
		return "activity/seldetailtip";
	}
	
	@RequestMapping("list.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> selectPager(HttpServletRequest req){
		IDetailTipService ser = FindServiceUtil.findService(IDetailTipService.class);
		return ser.selectDetailTipList(new ReqBo(req).setParam("areaCode", SessionUtil.get(BusinessContant.OPERAREAID)));
	}
	
	@RequestMapping("insert.htm")
	@ResponseBody
	public ResBo<?> insertDetailTip(@ModelAttribute DetailTip dt,@RequestParam("activityIds")String activityIds){
		List<Long> list = new ArrayList<Long>();
		for(String s:activityIds.split(",")){
			list.add(Long.valueOf(s));
		}
		dt.setAreaCode(SessionUtil.get(BusinessContant.OPERAREAID).toString());
		IDetailTipService ser = FindServiceUtil.findService(IDetailTipService.class);
		return ser.insertDetailTip(new ReqBo().setParam("ids", list).setParam("tip", dt));
	}
	
	@RequestMapping("rel.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> selectDetailTipPager(HttpServletRequest req,@RequestParam(value="tipId",required=false)Long tipId){
		if(tipId == null){
			return new ResBo<Pager<List<Map<String,Object>>>>(new Pager<List<Map<String,Object>>>());
		}
		IDetailTipService ser = FindServiceUtil.findService(IDetailTipService.class);
		return ser.selectDetailTipRel(new ReqBo(req));
	}
	
	@RequestMapping("del.htm")
	@ResponseBody
	public ResBo<?> deleteDetailTip(@RequestParam("tipId")long tipId){
		IDetailTipService ser = FindServiceUtil.findService(IDetailTipService.class);
		return ser.deleteDetailTip(new ReqBo().setParam("tipId", tipId));
	}
	
}
