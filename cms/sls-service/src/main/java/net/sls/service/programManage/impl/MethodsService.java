package net.sls.service.programManage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.component.programManage.IMethodsComponent;
import net.sls.dto.programManage.Methods;
import net.sls.service.programManage.IMethodsService;

@Service
public class MethodsService implements IMethodsService{
	
	@Autowired
	private IMethodsComponent methodsComponent;
	@Override
	public ResBo<Pager<List<Methods>>> selectMethodsList(ReqBo reqBo) {
		return new ResBo<Pager<List<Methods>>>(methodsComponent.selectMethodsList(reqBo.getParamInt("Iid"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	@Override
	public Methods selectMethodInfo(ReqBo reqBo) {
		return null;
	}
	@Override
	public ResBo<Methods> insertMethods(ReqBo reqBo) {
		Pager<List<Methods>> pager = methodsComponent.selectMethods(reqBo.getReqModel(Methods.class).getMethodName(),reqBo.getReqModel(Methods.class).getMethodEn(),reqBo.getReqModel(Methods.class).getIid(), 1, Integer.MAX_VALUE);
		if(pager.getTotal() > 0){
			return new ResBo<Methods>(14L);
		}
		Methods methods = methodsComponent.insertMethods(reqBo.getReqModel(Methods.class));
		return new ResBo<Methods>(methods);
	}
	@Override
	public ResBo<?> updateMethods(ReqBo reqBo) {
		methodsComponent.updateMethods(reqBo.getReqModel(Methods.class));
		return new ResBo<Object>();
	}

}
