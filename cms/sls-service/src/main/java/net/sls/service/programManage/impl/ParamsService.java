package net.sls.service.programManage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.component.programManage.IInterfaseComponent;
import net.sls.component.programManage.IMethodsComponent;
import net.sls.component.programManage.IParamsComponent;
import net.sls.dto.programManage.Interfase;
import net.sls.dto.programManage.Methods;
import net.sls.dto.programManage.Params;
import net.sls.service.programManage.IParamsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class ParamsService implements IParamsService{

	@Autowired
	private IParamsComponent paramsComponent;
	@Autowired
	private IMethodsComponent methodsComponent;
	@Autowired
	private IInterfaseComponent interfaseComponent;
	@Override
	public ResBo<List<Map<Object,Object>>> selectParamListBymethodId(ReqBo reqBo) {
		ResBo<List<Map<Object,Object>>> resBo = new ResBo<List<Map<Object,Object>>>();
		List<Map<Object, Object>> paramList = paramsComponent.selectParamListBymethodId(reqBo.getParamInt("methodId"));
		Map<Object, Object> map=new HashMap<Object, Object>();
		Methods methods = methodsComponent.selectMethodInfo(reqBo.getParamInt("methodId"));
		Interfase interfase = interfaseComponent.selectInterfacesInfo(methods.getIid());
		map.put("MethodName", methods.getMethodName());
		map.put("interfase", interfase.getInterfase());
		map.put("url", interfase.getUrl());
		paramList.add(map);
		resBo.setResult(paramList);
		return resBo;
	}
	@Override
	public ResBo<Pager<List<Params>>> selectParamsPagerBymethodId(ReqBo reqBo) {
		return new ResBo<Pager<List<Params>>>(paramsComponent.selectParamsPagerBymethodId(reqBo.getParamInt("methodId"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	@Override
	public ResBo<?> updateParams(ReqBo reqBo) {
		paramsComponent.updateParams(reqBo.getReqModel(Params.class));
		return new ResBo<Object>();
	}
	@Override
	public ResBo<Params> insertParams(ReqBo reqBo) {
		Pager<List<Params>> pager = paramsComponent.selectParams(reqBo.getReqModel(Params.class).getMethodId(),reqBo.getReqModel(Params.class).getParamsName(),reqBo.getReqModel(Params.class).getReqparams(), 1, Integer.MAX_VALUE);
		if(pager.getTotal() > 0){
			return new ResBo<Params>(14L);
		}
		Params params = paramsComponent.insertParams(reqBo.getReqModel(Params.class));
		return new ResBo<Params>(params);
	}
	
}
