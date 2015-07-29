package net.sls.service.impl.pc.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.component.pc.product.IAreaComponent;
import net.sls.component.pc.product.IPavilionAreaComponent;
import net.sls.component.pc.product.IPavilionInfoComponent;
import net.sls.dto.pc.product.PavilionInfo;
import net.sls.service.pc.product.IPavilionInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.Area;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
@Service("pavilionInfoService")
public class PavilionInfoService implements IPavilionInfoService{
	
	@Autowired
	private IPavilionInfoComponent pavilionInfoComponent;
	@Autowired
	private IAreaComponent areaComponent;
	@Autowired
	private IPavilionAreaComponent pavilionAreaC; 
	@Override
	public ResBo<Map<Object,Object>> getPavilionInfoById(ReqBo reqBo) {
		ResBo<Map<Object,Object>> resBo=new ResBo<Map<Object,Object>>();
		Map<Object,Object> map=new HashMap<Object, Object>();
		PavilionInfo pavilionInfo=new PavilionInfo();
		Area area=areaComponent.selectAreaById(reqBo.getParamInt("id"));
		pavilionInfo=pavilionInfoComponent.selectPavilionInfoByCode(area.getCode());
		map.put("mobile", pavilionInfo.getMobile());
		map.put("name", area.getName());
		map.put("code", area.getCode());
		resBo.setResult(map);
		return resBo;
	}

	@Override
	public ResBo<List<Map<Object, Object>>> getpavilionAreaById(ReqBo reqBo) {
		return new ResBo<List<Map<Object,Object>>>(pavilionAreaC.selectpavilionAreasById(reqBo.getParamInt("pavilionId")));
	}
	
}
