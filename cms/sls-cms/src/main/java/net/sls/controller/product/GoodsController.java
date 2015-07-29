package net.sls.controller.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.ext.product.GoodsAddBeanExt;
import net.sls.dto.product.Goods;
import net.sls.dto.product.GoodsArea;
import net.sls.dto.product.GoodsCategory;
import net.sls.dto.product.GoodsDetail;
import net.sls.dto.product.GoodsStock;
import net.sls.dto.user.CmsUser;
import net.sls.service.product.IAreaService;
import net.sls.service.product.IGoodsService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.framework.StrUtil;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 *
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
	
	@RequestMapping("addinit.htm")
	public String initAdd(Model model){
		String areaCode = StrUtil.toString(SessionUtil.get(BusinessContant.OPERAREAID));
		IAreaService service = FindServiceUtil.findService(IAreaService.class);
		model.addAttribute("areaName", service.selectAreaByCode(new ReqBo().setParam("code", areaCode)).getResult());
		return "goods/addGoods";
	}
	
	@RequestMapping("select.htm")
	public String initmemberSearch(){
		return "goods/selectGoods";
	}
	
	@RequestMapping("selectupdate.htm")
	@ResponseBody
	public ResBo<Map<String,Object>> selectUpdateGoodsInfo(@RequestParam("goodsId") long goodsId){
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		return goodsService.selectUpdateGoodsInfo(new ReqBo().setParam("goodsId", goodsId));
	}
	
	@RequestMapping("filter.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> selectGoodsByFilter(HttpServletRequest req){
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		return goodsService.selectGoodsByFilter(new ReqBo(req));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<?> insertGoods(HttpServletRequest req,@ModelAttribute Goods goods, @ModelAttribute GoodsDetail goodsDetail, @ModelAttribute GoodsStock goodsStock,@ModelAttribute GoodsCategory gc)throws Exception{
		String goodsAreaStr = req.getParameter("ga");
		ObjectMapper om = new ObjectMapper();
		List<GoodsArea> gas = new ArrayList<GoodsArea>();
		if(StringUtils.isNotBlank(goodsAreaStr)){
			 gas.addAll((List<GoodsArea>) om.readValue(goodsAreaStr, new TypeReference<List<GoodsArea>>() {}));
		}
		CmsUser cu = (CmsUser)SessionUtil.get(BusinessContant.CMSUSER);
		goods.setOperatorId(cu.getId());
		GoodsAddBeanExt goodsAddBeanExt = new GoodsAddBeanExt();
		goodsAddBeanExt.setGoods(goods);
		goodsAddBeanExt.setGoodsDetail(goodsDetail);
		goodsAddBeanExt.setGoodsStock(goodsStock);
		goodsAddBeanExt.setGoodsCategory(gc);
		goodsAddBeanExt.getGas().addAll(gas);
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		goodsService.insertGoods(new ReqBo(goodsAddBeanExt));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", goods.getId());
		map.put("sku", goods.getSku());
		return new ResBo<Map<String,Object>>(map);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("updateall.htm")
	@ResponseBody
	public ResBo<?> updateAll(HttpServletRequest req,@RequestParam("goodsId")Long goodsId,@ModelAttribute Goods goods, @ModelAttribute GoodsDetail goodsDetail, @ModelAttribute GoodsStock goodsStock,@ModelAttribute GoodsCategory gc) throws Exception{
		goods.setId(goodsId);
		String goodsAreaStr = req.getParameter("ga");
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		List<GoodsArea> gas = new ArrayList<GoodsArea>();
		if(StringUtils.isNotBlank(goodsAreaStr)){
			 gas.addAll((List<GoodsArea>) om.readValue(goodsAreaStr, new TypeReference<List<GoodsArea>>() {}));
			 for(GoodsArea ga:gas){
				 ga.setGoodsId(goodsId);
			 }
		}
		CmsUser cu = (CmsUser)SessionUtil.get(BusinessContant.CMSUSER);
		goods.setOperatorId(cu.getId());
		GoodsAddBeanExt goodsAddBeanExt = new GoodsAddBeanExt();
		goodsAddBeanExt.setGoods(goods);
		goodsAddBeanExt.setGoodsDetail(goodsDetail);
		goodsAddBeanExt.setGoodsStock(goodsStock);
		goodsAddBeanExt.setGoodsCategory(gc);
		goodsAddBeanExt.getGas().addAll(gas);
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		return goodsService.updateAll(new ReqBo(goodsAddBeanExt));
	}
	
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<?> updateGoods(@ModelAttribute Goods goods){
		goods.setModifytime(new Date());
		IGoodsService service = FindServiceUtil.findService(IGoodsService.class);
		return service.updateGoodsById(new ReqBo().setReqModel(goods));
	}
	
	@RequestMapping("getGoodsList.htm")
	@ResponseBody
	public List<ComboxDto> selectGoodsListByProvider(HttpServletRequest request){
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		ReqBo reqBo = new ReqBo(request);
		return goodsService.selectGoodsListByProvider(reqBo).getResult();
	}
	
	@RequestMapping("goodsList.htm")
	@ResponseBody
	public ResBo<Pager<List<Goods>>> getGoodsListByCategoryId(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		reqBo.setParam("areaCode", SessionUtil.get(BusinessContant.OPERAREAID));
		return goodsService.selectGoodsByCategoryId(reqBo);
	}
	
	@RequestMapping("batchissale.htm")
	@ResponseBody
	public ResBo<?> batchIsSale(@RequestParam("ids")String ids,@RequestParam("isSale")int isSale){
		List<Long> idList = new ArrayList<Long>();
		for(String id:ids.split(",")){
			idList.add(Long.parseLong(id));
		}
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		return goodsService.batchGoodsIsSale(new ReqBo().setParam("isSale", isSale).setParam("ids", idList));
	}
	
	@RequestMapping("flushbuynum.htm")
	@ResponseBody
	public ResBo<?> flush(){
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		return goodsService.flushBuyNum();
	}
	
	@RequestMapping("batchUseCoupon.htm")
	@ResponseBody
	public ResBo<?> batchUseCoupon(@RequestParam("ids")String ids){
		List<Long> idList = new ArrayList<Long>();
		for(String id:ids.split(",")){
			idList.add(Long.parseLong(id));
		}
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		return goodsService.batchUseCoupon(new ReqBo(idList).setParam("isUse",true));
	}
	
	@RequestMapping("batchNoUseCoupon.htm")
	@ResponseBody
	public ResBo<?> batchNoUseCoupon(@RequestParam("ids")String ids){
		List<Long> idList = new ArrayList<Long>();
		for(String id:ids.split(",")){
			idList.add(Long.parseLong(id));
		}
		IGoodsService goodsService = FindServiceUtil.findService(IGoodsService.class);
		return goodsService.batchUseCoupon(new ReqBo(idList).setParam("isUse",false));
	}
	
}
