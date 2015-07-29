package net.sls.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.product.Provider;
import net.sls.service.product.IProviderService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 供应商管理功能
 * 
 * */
@Controller
@RequestMapping("provider")
public class ProviderController {
	
	/**
	 * 
	 * 新增Provider供应商信息
	 * 
	 * */
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<Object> insertProvider(@ModelAttribute Provider Provider){
		IProviderService ds = FindServiceUtil.findService(IProviderService.class);
		return ds.insertProvider(new ReqBo(Provider));
	}
	
	/**
	 * 增加页面
	 * 
	 * */
	@RequestMapping("addinit.htm")
	public String insertInit(){
		return "provider/addinit";
	}
	
	/**
	 *  修改Provider供应商信息
	 * */
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<Provider> updateProvider(@ModelAttribute Provider provider){
		IProviderService ds = FindServiceUtil.findService(IProviderService.class);
		return ds.updateProvider(new ReqBo(provider));
	}
	
	@RequestMapping("navigation.htm")
	public String initProviderNavigation() {
		return "navigater/provider";
	}

	/**
	 * 
	 * 根据条件，分页查找供应商信息
	 * 
	 * */
	@RequestMapping(value = "infos.htm")
	@ResponseBody
	public ResBo<Pager<List<Provider>>> getProviderInfos(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IProviderService ps = FindServiceUtil.findService(IProviderService.class);
		return ps.selectProviders(reqBo);
	}
	
	/**
	 * 
	 * 查找供应商信息列表
	 * 
	 * */
	@RequestMapping(value = "getProvidersList.htm")
	@ResponseBody
	public List<ComboxDto> getProvidersList(
			HttpServletRequest request) {
		IProviderService ps = FindServiceUtil.findService(IProviderService.class);
		return ps.selectProviderList(new ReqBo().setParam("name", request.getParameter("q")).setParam("areaCode", SessionUtil.get(BusinessContant.OPERAREAID))).getResult();
	}
	
}
