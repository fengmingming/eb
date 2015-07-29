package net.sls.controller.area;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sls.businessconstant.BusinessContant;
import net.sls.dao.ext.product.AreaMapperExt;
import net.sls.dto.product.Area;
import net.sls.dto.product.PavilionInfo;
import net.sls.service.product.IAreaService;
import net.sls.service.product.IPavilionInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.model.ComboxDto;
import framework.web.ReqBo;
import framework.web.ResBo;


@Controller
@RequestMapping("area")
public class AreaController {
	
	private ObjectMapper om = new ObjectMapper();

	@RequestMapping("areas.htm")
	@ResponseBody
	public ResBo<List<Area>> getAreasByPid(@RequestParam("pid") int pid){
		IAreaService areaService = FindServiceUtil.findService(IAreaService.class);
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("pid", pid);
		return areaService.selectAreaListByPid(reqBo);
	}
	
	@RequestMapping("curUsrArea.htm")
	@ResponseBody
	public ResBo<List<ComboxDto>> getAreaByCodes(){
		String codes = (String)SessionUtil.get(BusinessContant.OPERAREAALL);
		IAreaService areaSvc = FindServiceUtil.findService(IAreaService.class);
		ReqBo rb = new ReqBo();
		String[] array = codes.split(",");
		if(array.length == 0){
			return new ResBo<List<ComboxDto>>();
		}
		rb.setParam("codes", array);
		ResBo<List<Area>> resb = areaSvc.selectAreasByCodes(rb);
		List<ComboxDto> list = transCode(resb.getResult());
		if(list.size() > 0){
			SessionUtil.replace(BusinessContant.OPERAREAID, list.get(0).getV());
		}
		return new ResBo<List<ComboxDto>>(list);		
	}
	
	
	@RequestMapping("combobox.htm")
	@ResponseBody
	public ResBo<List<ComboxDto>> getAreaComboboxByPid(@RequestParam("pid") int pid){
		IAreaService areaService = FindServiceUtil.findService(IAreaService.class);
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("pid", pid);
		ResBo<List<Area>> resBo = areaService.selectAreaListByPid(reqBo);		
		return new ResBo<List<ComboxDto>>(trans(resBo.getResult()));
	}
	
	/**
	 * 将Area对象集合转为ComboxDto对象的集合
	 * @param area
	 * @return
	 */
	private List<ComboxDto> trans(List<Area> area){
		List<ComboxDto> lst = new ArrayList<ComboxDto>();
		for(Area a:area){
			ComboxDto cd = new ComboxDto();
			cd.setK(a.getName());
			cd.setV(a.getId().toString());
			lst.add(cd);
		}
		return lst;
	}
	
	private List<ComboxDto> transCode(List<Area> area){
		List<ComboxDto> lst = new ArrayList<ComboxDto>();
		for(Area a:area){
			ComboxDto cd = new ComboxDto();
			cd.setK(a.getName());
			cd.setV(a.getCode());
			lst.add(cd);
		}
		return lst;
	}
	
	@RequestMapping("updatearea.htm")
	@ResponseBody
	public ResBo<?> updateArea(@ModelAttribute Area area){
		IAreaService areaService = FindServiceUtil.findService(IAreaService.class);
		return areaService.updateArea(new ReqBo().setReqModel(area));
	}
	
	@RequestMapping("insertarea.htm")
	@ResponseBody
	public ResBo<?> insertArea(@ModelAttribute Area area){
		ResBo<Object> resBo = new ResBo<Object>();
		area.setCreatetime(new Date());
		IAreaService areaService = FindServiceUtil.findService(IAreaService.class);
		areaService.insertArea(new ReqBo().setReqModel(area));
		// 如果code长度为15 则 是亭子
		if(area.getId() != null && area.getCode().length() == 15){
			PavilionInfo p=new PavilionInfo();
			p.setPavilionCode(area.getCode());
			p.setName(area.getName());
			IPavilionInfoService ps = FindServiceUtil.findService(IPavilionInfoService.class);
			ps.insertOrUpdatePavilionInfo(new ReqBo(p));
		}
		return resBo;
	}
	
	@RequestMapping("initarea.htm")
	public String initArea(){
		return "area/area";
	}
	
	@RequestMapping("childrenCode.htm")
	@ResponseBody
	public ResBo<Area> getAreaCodeByPid(@RequestParam("pid") int pid){
		IAreaService areaService = FindServiceUtil.findService(IAreaService.class);
		ResBo<Area> rs = areaService.selectAreaCodeByPid(new ReqBo().setParam("pid", pid));
		return rs;
	}
	
	@RequestMapping("areajs.htm")
	public void createAreaJs(HttpServletResponse res) throws Exception{
		AreaMapperExt mapper = FindServiceUtil.findService(AreaMapperExt.class);
		List<Area> list = mapper.selectAllArea();
		Map<Integer,String> map = new HashMap<Integer,String>();
		Map<Integer,List<AreaDto>> pmap = new HashMap<Integer,List<AreaDto>>();
		List<AreaDto> ads = new ArrayList<AreaDto>();
		for(Area area:list){
			map.put(area.getId(),area.getName());
			AreaDto ad = new AreaDto();
			ad.id = area.getId();
			ad.name = area.getName();
			ad.code = area.getCode();
			ad.pid = area.getPid();
			ads.add(ad);
			if(pmap.get(ad.pid) == null){
				pmap.put(ad.pid,new ArrayList<AreaDto>());
			}
			pmap.get(ad.pid).add(ad);
		}
		String str = "if(!window.framework){window.framework={};}window.framework.area_map=";
		str = str + om.writeValueAsString(map);
		AreaDto dto = new AreaDto();
		dto.id = 1;
		dto.name = "中国";
		dto.pid = 0;
		dto.code = "10";
		getChildren(dto,pmap);
		str = str + ";\r\n window.framework.area_tree="+om.writeValueAsString(dto)+";";
		str = str + "\r\n window.framework.area_pmap="+om.writeValueAsString(pmap)+";";
		res.setContentType("application/javascript; charset=utf-8");
		res.getWriter().write(str);
	}
	
	@SuppressWarnings("unused")
	private class AreaDto{
		private Integer id;
		private String name;
		private Integer pid;
		private String code;
		private List<AreaDto> children;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getPid() {
			return pid;
		}
		public void setPid(Integer pid) {
			this.pid = pid;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public List<AreaDto> getChildren() {
			return children;
		}
		public void setChildren(List<AreaDto> children) {
			this.children = children;
		}
	}
	
	private void getChildren(AreaDto dto,Map<Integer,List<AreaDto>> map){
		if(dto.children == null){
			dto.children = map.get(dto.id);
		}
		if(dto.children != null){
			for(AreaDto ad:dto.children){
				getChildren(ad,map);
			}
		}
	}
	
}
