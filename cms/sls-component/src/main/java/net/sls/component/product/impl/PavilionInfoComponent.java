package net.sls.component.product.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.product.IPavilionInfoComponent;
import net.sls.dao.ext.product.PavilionInfoMapperExt;
import net.sls.dao.product.PavilionAreaMapper;
import net.sls.dao.product.PavilionInfoMapper;
import net.sls.dto.product.PavilionArea;
import net.sls.dto.product.PavilionInfo;
import net.sls.dto.product.PavilionInfoExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class PavilionInfoComponent implements IPavilionInfoComponent {

	@Autowired
	private PavilionInfoMapperExt pavilionInfoMapperExt;
	
	@Autowired
	private PavilionInfoMapper pavilionInfoMapper;
	
	@Autowired
	private PavilionAreaMapper pam;
	
	@Override
	public void updatePavilionInfo(PavilionInfo record) {

		int i = pavilionInfoMapperExt.updatePavilionInfo(record);
		if(i < 1){
			throw new BusinessException(2L);
		}
		
	}

	@Override
	public Long selectPavilionCode(String pavilionCode) {

		Long pavilionCodeNo = pavilionInfoMapperExt.countSelectPavilionCode(pavilionCode);
		return pavilionCodeNo;
	}

	@Override
	public Long selectPavilionSn(String pavilionSn) {

		Long pavilionSnNo = pavilionInfoMapperExt.countSelectPavilionSn(pavilionSn);
		return pavilionSnNo;
	}

	@Override
	public void insertPavilionInfo(PavilionInfo record) {

		int i = pavilionInfoMapperExt.insertPavilionInfo(record);
		if(i < 1){
			throw new BusinessException(1L);
		}
	}

	@Override
	public Pager<List<Map<String,Object>>> selectPavilionInfo(String code, String pavilionCode,
			String pavilionSn,String mobile,String name,
			Integer start, Integer number) {

		Long pavilionInfoNo = pavilionInfoMapperExt.countSelectPavilionInfo(code ,pavilionCode,
				pavilionSn,mobile,name);
		List<Map<String,Object>> pavilionInfos = pavilionInfoMapperExt.selectPavilionInfo(code ,pavilionCode,
				pavilionSn,mobile,name,(start-1)*number, number);
		return new Pager<List<Map<String,Object>>>(pavilionInfos,pavilionInfoNo);
		
	}

	@Override
	public PavilionInfo selectPavilionByCode(String pavilionCode) {
		PavilionInfoExample e = new PavilionInfoExample();
		e.createCriteria().andPavilionCodeEqualTo(pavilionCode);
		List<PavilionInfo> list = pavilionInfoMapper.selectByExample(e);
		if(list==null||list.size()==0)
			return null;
		return list.get(0);
	}

	@Override
	public Pager<List<Map<String, Object>>> selectPavilionArea(Integer community, Integer pavilionId,Integer start,Integer number) {
		List<Map<String,Object>> list = pavilionInfoMapperExt.selectPavilionArea(community, pavilionId, start, number);
		long l = pavilionInfoMapperExt.countPavilionArea(community, pavilionId);
		return new Pager<List<Map<String,Object>>>(list, l);
	}

	@Override
	public void updatePavilionArea(PavilionArea pa) {
		pam.updateByPrimaryKeySelective(pa);
	}

	@Override
	public void insertPavilionArea(List<PavilionArea> list) {
		pavilionInfoMapperExt.insertPavilionArea(list);
	}

}
