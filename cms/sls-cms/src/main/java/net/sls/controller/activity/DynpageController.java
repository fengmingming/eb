package net.sls.controller.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.activity.IDynpageComponent;
import net.sls.dto.activity.Dynpage;
import net.sls.dto.activity.DynpageGoods;
import net.sls.dto.user.CmsUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ResBo;

@Controller
@RequestMapping("dynpage")
public class DynpageController {
	
	@RequestMapping("addpage.htm")
	public String init(){
		return "activity/dynpage";
	}
	
	@RequestMapping("selpage.htm")
	public String init2(){
		return "activity/selpage";
	}

	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<Long> addDynPage(@RequestParam("type")Integer type,@RequestParam("title")String title,@RequestParam("ids")String ids){
		CmsUser cu = (CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
		Dynpage dg = new Dynpage();
		dg.setTitle(title);
		dg.setCreatetime(new Date());
		dg.setCmsUserId(cu.getId());
		dg.setType(type);
		List<DynpageGoods> list = new ArrayList<DynpageGoods>();
		DynpageGoods dgoods = null;
		for(String id:ids.split(",")){
			dgoods = new DynpageGoods();
			dgoods.setGoodsId(Long.valueOf(id));
			list.add(dgoods);
		}
		IDynpageComponent com = FindServiceUtil.findService(IDynpageComponent.class);
		return new ResBo<Long>(com.insertDynpage(dg, list));
	}
	
	@RequestMapping("sel.htm")
	@ResponseBody
	public ResBo<Pager<List<Dynpage>>> selectDynpage(@RequestParam("page")int page,@RequestParam("rows")int number){
		IDynpageComponent com = FindServiceUtil.findService(IDynpageComponent.class);
		return new ResBo<Pager<List<Dynpage>>>(com.selectDynpage((page-1)*number, number));
	}
	
	@RequestMapping("goods.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> selectDynpageGoods(@RequestParam("dgId")long dgid,@RequestParam("page")int page,@RequestParam("rows")int number){
		IDynpageComponent com = FindServiceUtil.findService(IDynpageComponent.class);
		return new ResBo<Pager<List<Map<String,Object>>>>(com.selectDynPageGoods(dgid, (page-1)*number, number));
	}
	
	@RequestMapping("upddg.htm")
	@ResponseBody
	public ResBo<?> updateDynpage(@ModelAttribute Dynpage dg){
		IDynpageComponent com = FindServiceUtil.findService(IDynpageComponent.class);
		com.updateDynpage(dg);
		return new ResBo<Object>();
	}
	
	@RequestMapping("updGoods.htm")
	@ResponseBody
	public ResBo<?> updateDynpageGoods(@ModelAttribute DynpageGoods dg){
		IDynpageComponent com = FindServiceUtil.findService(IDynpageComponent.class);
		com.updateDynpageGoods(dg);
		return new ResBo<Object>();
	}
	
	
	@RequestMapping("deldgg.htm")
	@ResponseBody
	public ResBo<?> deleteDynpage(@RequestParam("id")long id){
		IDynpageComponent com = FindServiceUtil.findService(IDynpageComponent.class);
		com.deleteDynpageGoods(id);
		return new ResBo<Object>();
	}
}
