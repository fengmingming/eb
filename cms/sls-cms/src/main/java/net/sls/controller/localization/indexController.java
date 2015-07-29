package net.sls.controller.localization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.activity.Broadcast;
import net.sls.dto.activity.GoodsTop;
import net.sls.dto.user.CmsUser;
import net.sls.service.activity.IBroadcastService;
import net.sls.service.activity.IGoodsTopService;
import net.sls.service.product.IGoodsService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 处理首页面的本地化问题
 * @author sls006
 *
 */

@Controller
@RequestMapping("idx")
public class indexController {

	/**
	 * 处理首页面轮播图的问题，设置指定区域的轮播图地列表，如北京、上海等
	 * @param obj
	 * @return
	 */
	@RequestMapping("lightbox.htm")	
	public String lightbox(@ModelAttribute Object obj,String area){
		return "idx/lightbox";
	}
	
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<Broadcast> addMember(@ModelAttribute Broadcast bc){
		ResBo<Broadcast> resBo=new ResBo<Broadcast>();
		bc.setCreatetime(new Date());
		CmsUser u=(CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
		bc.setCmsUserId(u.getId());
		String areaCode = (String) SessionUtil.get(BusinessContant.OPERAREAID);
		if (areaCode.length() != 6) {
			return new ResBo<Broadcast>("该权限不是市级别管理员，无法提交轮播图");
		}
		bc.setAreaCode(areaCode);
		IBroadcastService bs = FindServiceUtil.findService(IBroadcastService.class);
		bs.insertBroadcast(new ReqBo(bc));
		resBo.setResult(bc);
		return resBo;
	}
	
	/**
	 * 轮播图查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="broadcastSearch.htm")
	@ResponseBody
	public ResBo<Pager<List<Broadcast>>> getBroadcastList(HttpServletRequest request){   
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam(BusinessContant.OPERAREAID, SessionUtil.get(BusinessContant.OPERAREAID));
		IBroadcastService bc = FindServiceUtil.findService(IBroadcastService.class);
		return bc.selectBroadcastList(reqBo);
	}
	
	/**
	 *  修改轮播图信息
	 * */
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<Broadcast> updateBroadcast(@ModelAttribute Broadcast bc){
		IBroadcastService ibc = FindServiceUtil.findService(IBroadcastService.class);
		return ibc.updateBroadcast(new ReqBo(bc));
	}
	
	/**
	 * 处理首页面轮播图的问题，设置指定区域的轮播图地列表，如北京、上海等
	 * @param obj
	 * @return
	 */
	@RequestMapping("topn.htm")

	public String topn(@ModelAttribute Object obj,String area){
		return "idx/topn";
	}
	
	/**
	 * 人气商品批量添加
	 * @param goodsTop
	 * @return
	 */
	@RequestMapping("batchaddTopn.htm")
	@ResponseBody
	public ResBo<?> batchaddTopn(@RequestParam("ids")String ids,@RequestParam("isDel")int isDel,@RequestParam("sort")int sort){
		CmsUser u=(CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
		String areaCode = (String) SessionUtil.get(BusinessContant.OPERAREAID);
		if (areaCode.length() != 6) {
			return new ResBo<Object>("该权限不是市级别管理员，无法提交轮播图");
		}
		List<Long> idList = new ArrayList<Long>();
		for(String id:ids.split(",")){
			idList.add(Long.parseLong(id));
		}
		IGoodsTopService bs = FindServiceUtil.findService(IGoodsTopService.class);
		return bs.insertGoodsTop(new ReqBo().setParam("ids", idList).setParam("isDel", isDel).setParam("sort", sort).setParam("areaCode", areaCode).setParam("userId", u.getId()));
	}
	
	/**
	 * 人气商品批量删除
	 * @param goodsTop
	 * @return
	 */
	@RequestMapping("batchDeleteTopn.htm")
	@ResponseBody
	public ResBo<?> batchDeleteTopn(@RequestParam("ids")String ids){
		List<Long> idList = new ArrayList<Long>();
		for(String id:ids.split(",")){
			idList.add(Long.parseLong(id));
		}
		IGoodsTopService bs = FindServiceUtil.findService(IGoodsTopService.class);
		return bs.updateGoodsTopIsDel(new ReqBo().setParam("ids", idList));
	}
	
	/**
	 *  修改人气商品信息
	 * */
	@RequestMapping("updateGoodsTop.htm")
	@ResponseBody
	public ResBo<GoodsTop> updateGoodsTop(@ModelAttribute GoodsTop gt){
		IGoodsTopService ibc = FindServiceUtil.findService(IGoodsTopService.class);
		return ibc.updateGoodsTop(new ReqBo(gt));
	}
	
	/**
	 * 人气商品查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="goodsTopSearch.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<Object,Object>>>> getGoodsTop(HttpServletRequest request){   
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam(BusinessContant.OPERAREAID, SessionUtil.get(BusinessContant.OPERAREAID));
		IGoodsTopService bc = FindServiceUtil.findService(IGoodsTopService.class);
		return bc.selectGoodsTopList(reqBo);
	}
	
}
