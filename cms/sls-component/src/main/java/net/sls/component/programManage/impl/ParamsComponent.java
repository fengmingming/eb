package net.sls.component.programManage.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.programManage.IParamsComponent;
import net.sls.dao.ext.programManage.ParamsMapperExt;
import net.sls.dto.programManage.Params;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;
@Component
public class ParamsComponent implements IParamsComponent{
	@Autowired
	private ParamsMapperExt paramsMapperExt;

	@Override
	public List<Map<Object,Object>> selectParamListBymethodId(Integer methodId) {
		return paramsMapperExt.selectParamListBymethodId(methodId);
	}

	@Override
	public Pager<List<Params>> selectParamsPagerBymethodId(
			Integer methodId, Integer start, Integer number) {
		List<Params> list = paramsMapperExt.selectParamsPagerBymethodId(methodId,(start-1)*number, number);
		long count = paramsMapperExt.countParamsByFilter(methodId);
		Pager<List<Params>> pager = new Pager<List<Params>>(list,count);
		return pager;
	}

	@Override
	public Pager<List<Params>> selectParams(Long methodId,String paramsName,
			String reqparams, Integer start, Integer number) {
		List<Params> list = paramsMapperExt.selectParams(methodId,paramsName,reqparams,(start-1)*number, number);
		long count = paramsMapperExt.countParamsByFilters(methodId,paramsName,reqparams);
		Pager<List<Params>> pager = new Pager<List<Params>>(list,count);
		return pager;
	}

	@Override
	public Params insertParams(Params params) {
		int i = paramsMapperExt.insertParamsReturnId(params);
		if(i!=1){
			throw new BusinessException(1L);
		}
		return params;
	}

	@Override
	public Params updateParams(Params params) {
		int i = paramsMapperExt.updateParamsByPrimaryKey(params);
		if(i!=1){
			throw new BusinessException(2L);
		}
		return params;
	}
}
