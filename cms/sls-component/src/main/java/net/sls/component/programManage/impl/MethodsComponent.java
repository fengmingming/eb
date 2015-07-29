package net.sls.component.programManage.impl;

import java.util.List;

import net.sls.component.programManage.IMethodsComponent;
import net.sls.dao.ext.programManage.MethodsMapperExt;
import net.sls.dto.programManage.Methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class MethodsComponent implements IMethodsComponent{
	
	@Autowired
	private MethodsMapperExt methodsMapperExt;
	@Override
	public Pager<List<Methods>> selectMethodsList(Integer Iid,Integer start,Integer number) {
		List<Methods> list = methodsMapperExt.selectMethodsList(Iid,(start-1)*number, number);
		long count = methodsMapperExt.countMethodsByFilter( Iid);
		Pager<List<Methods>> pager = new Pager<List<Methods>>(list,count);
		return pager;
	}
	@Override
	public Methods selectMethodInfo(Integer id) {
		Methods methods = methodsMapperExt.selectMethodInfo( id);
		return methods;
	}
	@Override
	public Methods insertMethods(Methods methods) {
		int i = methodsMapperExt.insertMethodsReturnId(methods);
		if(i!=1){
			throw new BusinessException(1L);
		}
		return methods;
	}
	@Override
	public Methods updateMethods(Methods methods) {
		int i = methodsMapperExt.updateMethodsByPrimaryKey(methods);
		if(i!=1){
			throw new BusinessException(2L);
		}
		return methods;
	}
	@Override
	public Pager<List<Methods>> selectMethods(String methodName,
			String methodEn, Long Iid, Integer start, Integer maxValue) {
		List<Methods> list = methodsMapperExt.selectMethods(methodName,methodEn,Iid,(start-1)*maxValue, maxValue);
		long count = methodsMapperExt.countMethodsByFilters(methodName,methodEn,Iid);
		Pager<List<Methods>> pager = new Pager<List<Methods>>(list,count);
		return pager;
	}

}
