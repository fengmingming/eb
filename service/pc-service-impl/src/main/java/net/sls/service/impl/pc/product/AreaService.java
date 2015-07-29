package net.sls.service.impl.pc.product;

import java.util.ArrayList;
import java.util.List;

import net.sls.component.pc.product.IAreaComponent;
import net.sls.service.pc.product.IAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.Area;
import util.model.AreaEnum;
import util.model.ComboxDto;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service("areaService")
public class AreaService implements IAreaService{

	@Autowired
	private IAreaComponent areaComponent;
	@Override
	public ResBo<List<ComboxDto>> getAreaList(ReqBo reqBo) {
		ResBo<List<ComboxDto>> resBo=new ResBo<List<ComboxDto>>(); 
		List<ComboxDto> areaList=areaComponent.selectAreaList(reqBo.getParamInt("pid"));
		resBo.setResult(areaList);
		return resBo;
	}
	
	public ResBo<List<Area>> getAreasList(ReqBo reqBo){
		ResBo<List<Area>> resBo=new ResBo<List<Area>>(); 
		List<Area> areaList=new ArrayList<Area>();
		Area area=AreaEnum.getArea(reqBo.getParamInt("id"));
		if (null != area) {
			areaList.add(area);
			areaList.addAll(getArea(area));
			}else{
				Area areaT=areaComponent.selectAreaById(reqBo.getParamInt("id"));
				areaList.add(areaT);
				areaList.addAll(getAreaT(areaT));
		}
		resBo.setResult(areaList);
		return resBo;
	}
	
	public List<Area> getArea(Area area){
		List<Area> areaList=new ArrayList<Area>();
		if (null != area) {
			if (1 != area.getPid()) {
				Area areaT = AreaEnum.getArea(area.getPid());
				if (null != areaT) {
					areaList.add(areaT);
				}else{
					getAreaT(area);
				}
			}
		}
		return areaList;
	}
	
	public List<Area> getAreaT(Area area){
		List<Area> areaList=new ArrayList<Area>();
		Area areaT=areaComponent.selectAreaById(area.getPid());
		if (null != areaT) {
			if (1 != areaT.getPid()) {
				areaList.add(areaT);
//				AreaEnum.setArea(areaT.getId(), areaT);
				getAreaT(area);
			}else{
				areaList.add(areaT);
//				AreaEnum.setArea(areaT.getId(), areaT);
			}
		}
		return areaList;
	}
}
