package net.sls.controller.product;

import java.util.List;

import net.sls.dto.product.GoodsArea;
import net.sls.service.product.IGoodsAreaService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("goodsarea")
public class GoodsAreaController {
	
	
	@RequestMapping("list.htm")
	@ResponseBody
	public ResBo<Pager<List<GoodsArea>>> selectAreaList(@RequestParam(value="goodsId",required=false) Integer goodsId){
		ResBo<Pager<List<GoodsArea>>> res = new ResBo<Pager<List<GoodsArea>>>();
		if(goodsId == null){
			res.setSuccess(false);
			return res;
		}
		IGoodsAreaService goodsAreaService = FindServiceUtil.findService(IGoodsAreaService.class);
		return goodsAreaService.selectAreaPager(new ReqBo().setParam("goodsId", goodsId));
	}
	
	@RequestMapping("delete.htm")
	@ResponseBody
	public ResBo<?> deleteGoodsArea(@RequestParam("id")long id){
		IGoodsAreaService goodsAreaService = FindServiceUtil.findService(IGoodsAreaService.class);
		return goodsAreaService.deleteGoodsAreaById(new ReqBo().setParam("id", id));
	}
}
