package net.sls.service.product.impl;

import java.util.List;

import net.sls.component.product.IAreaComponent;
import net.sls.component.product.IPavilionInfoComponent;
import net.sls.dto.product.Area;
import net.sls.service.product.IAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class AreaService implements IAreaService{

	@Autowired
	private IAreaComponent areaComponent;
	@Autowired
	private IPavilionInfoComponent pavilionInfoComponent;
	
	@Override
	public ResBo<List<Area>> selectAreaListByPid(ReqBo reqBo) {
		return new ResBo<List<Area>>(areaComponent.selectAreaByPid(reqBo.getParamInt("pid")));
	}

	@Override
	public ResBo<?> insertArea(ReqBo reqBo) {
		Area area = reqBo.getReqModel(Area.class);
		areaComponent.insertArea(area);
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> updateArea(ReqBo reqBo) {
		areaComponent.updateArea(reqBo.getReqModel(Area.class));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Area> selectAreaById(ReqBo reqBo) {
		return new ResBo<Area>(areaComponent.selectAreaById(reqBo.getParamInt("id")));
	}
	
	

	@Override
	public ResBo<Object> countSelectCode(ReqBo reqBo) {
		String code = reqBo.getParamStr("code");
		Long codeNo = areaComponent.selectCode(code);
		return new ResBo<Object>(codeNo);
	}

	@Override
	public ResBo<String> selectAreaByCode(ReqBo reqBo) {
		ResBo<String> res = new ResBo<String>();
		res.setResult(areaComponent.selectAreaByCode(reqBo.getParamStr("code")).getName());
		return res;
	}

	@Override
	/**
	 * 通过code 集合取得
	 */
	public ResBo<List<Area>> selectAreasByCodes(ReqBo rb) {
		return new ResBo<List<Area>>(areaComponent.selectAreasByCodes((String[])rb.getParam("codes")));		
	}

	@Override
	public ResBo<Area> selectAreaCodeByPid(ReqBo reqBo) {
		return new ResBo<Area>(areaComponent.selectAreaCodeByPid(reqBo.getParamInt("pid")));
	}

}
