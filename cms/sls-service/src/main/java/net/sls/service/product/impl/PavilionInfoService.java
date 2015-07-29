package net.sls.service.product.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.product.IAreaComponent;
import net.sls.component.product.IPavilionInfoComponent;
import net.sls.dto.product.PavilionArea;
import net.sls.dto.product.PavilionInfo;
import net.sls.service.product.IPavilionInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class PavilionInfoService implements IPavilionInfoService {
	
	@Autowired
	public IPavilionInfoComponent pavilionInfoComponent;

	@Autowired
	public IAreaComponent areaComponent;
	
	@Override
	public ResBo<Object> updatePavilionInfo(ReqBo reqBo) {

		PavilionInfo pavilionInfo = reqBo.getReqModel(PavilionInfo.class);
		pavilionInfoComponent.updatePavilionInfo(pavilionInfo);
		
		return new ResBo<Object>("修改成功");
	}

	@Override
	public ResBo<Object> insertOrUpdatePavilionInfo(ReqBo reqBo) {

		PavilionInfo pavilionInfo = reqBo.getReqModel(PavilionInfo.class);
		
		//亭子的id是否存在，不在增加，在的话说明有，更新
		if(pavilionInfo.getId()==null){
			//增加亭子信息
			String code = pavilionInfo.getPavilionCode();
			Long pavilionSnNo = pavilionInfoComponent.selectPavilionSn(pavilionInfo.getPavilionSn());
			if (pavilionSnNo > 0) {
				//pavilionSnNo亭子编号是否已经用过
				return new ResBo<Object>(25);
			}
			pavilionInfo.setProvince(areaComponent.selectKey(code.substring(0,3)));
			
			pavilionInfo.setCity(areaComponent.selectKey(code.substring(0,6)));
			
			pavilionInfo.setDistrict(areaComponent.selectKey(code.substring(0,9)));
			pavilionInfo.setCommunity(areaComponent.selectKey(code.substring(0,12)));
			pavilionInfoComponent.insertPavilionInfo(pavilionInfo);
			
		}else{
			//修改亭子信息
			pavilionInfoComponent.updatePavilionInfo(pavilionInfo);
		}
		return new ResBo<Object>();
		
	}


	@Override
	public ResBo<Pager<List<Map<String,Object>>>> selectPavilionInfo(ReqBo reqBo) {

		Integer number = reqBo.getParamInt("rows");
		Integer start =reqBo.getParamInt("page");
		String code = reqBo.getParamStr("code");
		String pavilionCode = reqBo.getParamStr("pavilionCode");
		String pavilionSn = reqBo.getParamStr("pavilionSn");
		String mobile = reqBo.getParamStr("mobile");
		String name = reqBo.getParamStr("name");
		Pager<List<Map<String,Object>>> pavilionInfos = pavilionInfoComponent.selectPavilionInfo(code,pavilionCode,
				pavilionSn,mobile,name,start, number);
		return new ResBo<Pager<List<Map<String,Object>>>>(pavilionInfos);
	}

	@Override
	public ResBo<PavilionInfo> selectPavilionByCode(ReqBo reqBo) {
		String pavilionCode = reqBo.getParamStr("code");
		if(pavilionCode==null||pavilionCode=="")
			return null;
		PavilionInfo pavilion = pavilionInfoComponent.selectPavilionByCode(pavilionCode );
		
		return new ResBo<PavilionInfo>(pavilion);
	}

	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectPavilionArea(
			ReqBo reqBo) {
		int page = reqBo.getParamInt("page");
		int rows = reqBo.getParamInt("rows");
		return new ResBo<Pager<List<Map<String, Object>>>>(pavilionInfoComponent.selectPavilionArea(reqBo.getParamInt("communityId"), reqBo.getParamInt("pavilionId"), (page-1)*rows, rows));
	}

	@Override
	public ResBo<?> upPavilionArea(ReqBo reqBo) {
		pavilionInfoComponent.updatePavilionArea(reqBo.getReqModel(PavilionArea.class));
		return new ResBo<Object>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResBo<?> addPavilionArea(ReqBo reqBo) {
		pavilionInfoComponent.insertPavilionArea((List<PavilionArea>) reqBo.getReqModel());
		return new ResBo<Object>();
	}
	
	

}
