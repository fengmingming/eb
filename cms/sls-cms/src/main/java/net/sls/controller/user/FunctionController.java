package net.sls.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.user.Function;
import net.sls.service.user.IFunctionService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("function")
public class FunctionController {
	
	/**@author wangshaohui
	 * @Description: TODO
	 * @param pid
	 * @return ResBo<List<Map<String,Object>>>
	 * @date 2014年12月17日 下午4:24:50
	 */
	@RequestMapping("getnode.htm")
	@ResponseBody
	public ResBo<List<Map<String,Object>>> getFunTreeById(@RequestParam("fid") int pid){
		Integer id = pid;
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("id", id);
		IFunctionService FunctionService = FindServiceUtil.findService(IFunctionService.class);
		return FunctionService.selectFunctionsByPid(reqBo);
	}
	/**
	 * @author wangguojun
	 * @description: 跳转到"功能查改"页面
	 * @return String
	 * @date 2014年12月9日 下午2:33:38
	 */
	@RequestMapping("search.htm")
	public String initmemberSearch(){
		return "function/selectFunctions";
	}
	
	/**
	 * @author wangguojun
	 * @description: 跳转到"添加功能"页面
	 * @return String
	 * @date 2014年12月9日 下午2:36:27
	 */
	@RequestMapping("addFunctions.htm")
	public String addFunctionsSearch(){
		return "function/addFunctions";
	}

	/**
	 * @author wangguojun
	 * @description: 添加功能
	 * @param function functionName fid
	 * @return ResBo<Object>
	 * @date 2014年12月9日 下午2:37:06
	 */
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<Object> insertCategory(@RequestParam(value="isImportant",defaultValue="false",required=false)Boolean isImportant,@ModelAttribute Function function,@RequestParam("functionName") String functionName,@RequestParam("fid") Integer fid){
		IFunctionService ds = FindServiceUtil.findService(IFunctionService.class);
//		从页面上获取的fid就是pid
		function.setFatherId(fid);
		function.setName(functionName);
		return ds.insertFunction(new ReqBo(function).setParam("isImportant", isImportant));
	}
	/**
	 * @author wangguojun
	 * @description: 可选择条件的修改功能
	 * @param function functionUrl
	 * @return ResBo<Object>
	 * @date 2014年12月9日 下午2:39:11
	 */
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<Object> updateByPrimaryKeySelective(@ModelAttribute Function function,@RequestParam("functionUrl") String functionUrl){
		IFunctionService functionService = FindServiceUtil.findService(IFunctionService.class);
		function.setUrl(functionUrl);
		return functionService.updateByPrimaryKeySelective(new ReqBo(function));
	}
	
	/**
	 * @author wangguojun
	 * @description: 删除功能
	 * @param function
	 * @return ResBo<Object>
	 * @date 2014年12月9日 下午2:40:10
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public ResBo<Object> deleteByPrimaryKey(@ModelAttribute Function function){
		IFunctionService functionService = FindServiceUtil.findService(IFunctionService.class);
		return functionService.deleteByPrimaryKey(new ReqBo(function));
	}
	
	/**
	 * @author wangguojun
	 * @description: 按父类节点id查询功能
	 * @param function
	 * @return ResBo<List<Function>>
	 * @date 2014年12月9日 下午2:40:25
	 */
	@RequestMapping("select.htm")
	@ResponseBody
	public ResBo<List<Function>> selectFunctionByFatherId(@ModelAttribute Function function){
		IFunctionService functionService = FindServiceUtil.findService(IFunctionService.class);
		return functionService.selectFunctionByFatherId(new ReqBo(function));
	}
	
	/**
	 * @author wangguojun
	 * @description: 可选择条件的查询功能并且分页
	 * @param request
	 * @return ResBo<Pager<List<Function>>>
	 * @date 2014年12月9日 下午2:40:45
	 */
	@RequestMapping("select-filter.htm")
	@ResponseBody
	public ResBo<Pager<List<Function>>> getFunctionInfos(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IFunctionService functionService = FindServiceUtil.findService(IFunctionService.class);
		return functionService.selectFunctionByFilter(reqBo);
	}
	
}
