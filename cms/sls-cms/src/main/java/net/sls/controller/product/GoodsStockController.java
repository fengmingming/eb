package net.sls.controller.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.product.GoodsStock;
import net.sls.service.product.IGoodsStockService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 商品库存管理功能
 * 
 * */
@Controller
@RequestMapping("goodsStock")
public class GoodsStockController {
	
	/**
	 * 增加页面
	 * 
	 * */
	@RequestMapping("init.htm")
	public String insertInit(){
		return "goodsStock/addinit";
	}
	
	/**
	 *  修改GoodsStock商品库存信息
	 * */
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<GoodsStock> updateGoodsStock(@ModelAttribute GoodsStock goodsStock){
		goodsStock.setModifyTime(new Date());
		IGoodsStockService ds = FindServiceUtil.findService(IGoodsStockService.class);
		return ds.updateGoodsStock(new ReqBo(goodsStock));
	}
	
	@RequestMapping("selectpager.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> selectGoodsStocks(HttpServletRequest req){
		IGoodsStockService ds = FindServiceUtil.findService(IGoodsStockService.class);
		return ds.selectGoodsStocks(new ReqBo(req).setParam("areaCode", SessionUtil.get(BusinessContant.OPERAREAID)));
	}
	
}
