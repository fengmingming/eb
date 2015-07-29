package net.sls.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.product.GoodsCategory;
import net.sls.service.product.IGoodsCategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 商品品类关系管理功能
 * 
 * */
@Controller
@RequestMapping("goodsCategory")
public class GoodsCategoryController {
	
	/**
	 *  修改GoodsCategory商品品类关系信息
	 * */
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<GoodsCategory> updateGoodsCategory(@ModelAttribute GoodsCategory goodsCategory){
		IGoodsCategoryService ds = FindServiceUtil.findService(IGoodsCategoryService.class);
		return ds.updateGoodsCategory(new ReqBo(goodsCategory));
	}
	
	@RequestMapping("navigation.htm")
	public String initGoodsCategoryNavigation() {
		return "navigater/goodsCategory";
	}

	/**
	 * 
	 * 根据条件，分页查找商品品类关系信息
	 * 
	 * */
	@RequestMapping(value = "infos.htm")
	@ResponseBody
	public ResBo<Pager<List<GoodsCategory>>> getGoodsCategoryInfos(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IGoodsCategoryService ps = FindServiceUtil.findService(IGoodsCategoryService.class);
		return ps.selectGoodsCategorys(reqBo);
	}
	
	
	@RequestMapping(value = "add.htm")
	@ResponseBody
	public ResBo<?> insertGoodsCategory(@ModelAttribute GoodsCategory gc){
		IGoodsCategoryService ps = FindServiceUtil.findService(IGoodsCategoryService.class);
		return ps.insertGoodsCategory(new ReqBo().setReqModel(gc));
	}
	
	@RequestMapping("updateCategory.htm")
	@ResponseBody
	public ResBo<Object> updateByGoodsIdSelective(@RequestParam("goodsIds") String goodsIds,
			@RequestParam("oneId") Long oneId,@RequestParam("twoId") Long twoId,@RequestParam("threeId") Long threeId){
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("oneId", oneId);
		reqBo.setParam("twoId", twoId);
		reqBo.setParam("threeId", threeId);
		reqBo.setParam("goodsIds", goodsIds);
		IGoodsCategoryService ps = FindServiceUtil.findService(IGoodsCategoryService.class);
		return ps.updateByGoodsIdSelective(reqBo);
	}
}
