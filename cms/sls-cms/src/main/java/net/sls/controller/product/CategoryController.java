package net.sls.controller.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.product.Category;
import net.sls.service.product.ICategoryService;

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
 * 种类管理功能
 * 
 * */
@Controller
@RequestMapping("category")
public class CategoryController {
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
		ICategoryService categoryService = FindServiceUtil.findService(ICategoryService.class);
		return categoryService.selectCategorysByPid(reqBo);
	}
	/**
	 * 
	 * 新增Category种类信息
	 * 
	 * */
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<Object> insertCategory(@ModelAttribute Category category,@RequestParam("categoryName") String categoryName,@RequestParam("fid") Long fid){
		ICategoryService ds = FindServiceUtil.findService(ICategoryService.class);
//		从页面上获取的fid就是pid
		category.setParentId(fid);
		category.setName(categoryName);
		category.setIsUse(true);
		return ds.insertCategory(new ReqBo(category));
	}
	
	/**
	 * 增加页面
	 * 
	 * */
	@RequestMapping("addinit.htm")
	public String insertInit(){
		return "category/addinit";
	}
	
	/**
	 *  修改Category种类信息
	 * */
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<Category> updateCategory(@ModelAttribute Category category){
		ICategoryService ds = FindServiceUtil.findService(ICategoryService.class);
		category.setIsUse(true);
		return ds.updateCategory(new ReqBo(category));
	}
	
	/**
	 *  删除Category品类节点
	 * */
	@RequestMapping("delete.htm")
	@ResponseBody
	public ResBo<Object> deleteCategory(@RequestParam("fid") Long id){
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("id", id);
		ICategoryService categoryService = FindServiceUtil.findService(ICategoryService.class);
		return categoryService.deleteCategory(reqBo);
	}
	
	@RequestMapping("navigation.htm")
	public String initCategoryNavigation() {
		return "navigater/category";
	}

	/**
	 * 
	 * 根据条件，分页查找种类信息
	 * 
	 * */
	@RequestMapping(value = "infos.htm")
	@ResponseBody
	public ResBo<Pager<List<Category>>> getCategoryInfos(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		ICategoryService ps = FindServiceUtil.findService(ICategoryService.class);
		ResBo<Pager<List<Category>>> resBo = ps.selectCategorys(reqBo);
		return resBo;
	}
	
	@RequestMapping("combobox.htm")
	@ResponseBody
	public ResBo<List<ComboxDto>> getComboboxByPId(@RequestParam("pid") int pid){
		return FindServiceUtil.findService(ICategoryService.class).selectCategoryComboxByPid(new ReqBo().setParam("pid", pid));
	}
	
	@RequestMapping("CategoryCode.htm")
	@ResponseBody
	public ResBo<Category> getCategoryByPId(@RequestParam("pid") int pid){
		return FindServiceUtil.findService(ICategoryService.class).selectCategoryCodeByPId(new ReqBo().setParam("parentId", pid));
	}
	
}
