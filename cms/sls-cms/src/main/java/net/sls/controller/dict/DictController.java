package net.sls.controller.dict;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.product.Dict;
import net.sls.service.dict.IDictService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 后台字典管理功能
 * 
 * */
@Controller
@RequestMapping("dict")
public class DictController {
	
	/**
	 * 
	 * 新增dict字典信息
	 * 
	 * */
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<Object> insertDict(@ModelAttribute Dict dict){
		IDictService ds = FindServiceUtil.findService(IDictService.class);
		return ds.insertDict(new ReqBo(dict));
	}
	
	/**
	 * 增加页面
	 * 
	 * */
	@RequestMapping("addinit.htm")
	public String insertInit(){
		return "dict/addinit";
	}
	
	/**
	 *  修改dict字典信息
	 * */
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<Dict> updateDict(@ModelAttribute Dict dict){
		IDictService ds = FindServiceUtil.findService(IDictService.class);
		return ds.updateDict(new ReqBo(dict));
	}
	
	@RequestMapping("navigation.htm")
	public String initDictNavigation() {
		return "navigater/dict";
	}

	/**
	 * 
	 * 根据条件，分页查找字典信息
	 * 
	 * */
	@RequestMapping(value = "infos.htm")
	@ResponseBody
	public ResBo<Pager<List<Dict>>> getDictInfos(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IDictService ds = FindServiceUtil.findService(IDictService.class);
		return ds.selectDicts(reqBo);
	}
	
	@RequestMapping("combox.htm")
	@ResponseBody
	public ResBo<List<ComboxDto>> selectDictCombox(){
		IDictService ds = FindServiceUtil.findService(IDictService.class);
		return ds.selectDictCombox(new ReqBo());
	}
	
	@RequestMapping("query.htm")
	@ResponseBody
	public List<ComboxDto> selectDictComboxByType(@RequestParam(value="type",required=false)Integer type,@RequestParam(value="q",required=false)String key){
		IDictService ds = FindServiceUtil.findService(IDictService.class);
		return ds.selectDictComboxByType(new ReqBo().setParam("type", type).setParam("key", key)).getResult();
	} 
	
}
