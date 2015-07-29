package net.sls.controller.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.product.PavilionArea;
import net.sls.dto.product.PavilionInfo;
import net.sls.service.product.IPavilionInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.framework.StrUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("pavilionInfo")
public class PavilionInfoController {
	
	private ObjectMapper om = new ObjectMapper();

	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<Object> updatePavilionInfo(@ModelAttribute PavilionInfo pavilionInfo){
		IPavilionInfoService ps = FindServiceUtil.findService(IPavilionInfoService.class);
		return ps.updatePavilionInfo(new ReqBo(pavilionInfo));
	}
	
	@RequestMapping("addOrUpdate.htm")
	@ResponseBody
	public ResBo<Object> insertPavilionInfo(@ModelAttribute PavilionInfo pavilionInfo){
		IPavilionInfoService ps = FindServiceUtil.findService(IPavilionInfoService.class);
		return ps.insertOrUpdatePavilionInfo(new ReqBo(pavilionInfo));
	}
	
	@RequestMapping("selectPavilion.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> selectPavilionInfo(HttpServletRequest pavilionInfo){
		IPavilionInfoService ps = FindServiceUtil.findService(IPavilionInfoService.class);
		ReqBo reqBo = new ReqBo(pavilionInfo);
		String code = StrUtil.toString(SessionUtil.get(BusinessContant.OPERAREAID));
		reqBo.setParam("code", code);
		return ps.selectPavilionInfo(reqBo);
	}
	
	@RequestMapping("selectPavilionByCode.htm")
	@ResponseBody
	public ResBo<PavilionInfo> selectPavilionByCode(HttpServletRequest pavilionInfo){
		IPavilionInfoService ps = FindServiceUtil.findService(IPavilionInfoService.class);
		return ps.selectPavilionByCode(new ReqBo(pavilionInfo));
	}
	
	@RequestMapping("addinit.htm")
	public String initAdd(){
		return "pavilionInfo/addinit";
	}
	
	@RequestMapping("select.htm")
	public String initmemberSearch(){
		return "pavilionInfo/selectPavilionInfo";
	}
	
	@RequestMapping("circum.htm")
	public String pavilionArea(){
		return "pavilionInfo/circum";
	}
	
	@RequestMapping("pavilionarea.htm")
	@ResponseBody
	public ResBo<?> selectPavilionAreaList(HttpServletRequest req){
		IPavilionInfoService ps = FindServiceUtil.findService(IPavilionInfoService.class);
		return ps.selectPavilionArea(new ReqBo(req));
	}
	
	@RequestMapping("addPa.htm")
	@ResponseBody
	public ResBo<?> addPa(@RequestParam("data")String pa) throws Exception{
		IPavilionInfoService ps = FindServiceUtil.findService(IPavilionInfoService.class);
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<PavilionArea> list = om.readValue(pa, new TypeReference<List<PavilionArea>>() {});
		return ps.addPavilionArea(new ReqBo(list));
	}
	
	
	@RequestMapping("upcircum.htm")
	@ResponseBody
	public ResBo<?> upcircum(@ModelAttribute PavilionArea pa){
		IPavilionInfoService ps = FindServiceUtil.findService(IPavilionInfoService.class);
		return ps.upPavilionArea(new ReqBo(pa));
	}
	
}
