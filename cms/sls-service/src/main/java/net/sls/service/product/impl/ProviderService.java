package net.sls.service.product.impl;

import java.util.Date;
import java.util.List;

import net.sls.component.product.IProviderComponent;
import net.sls.dto.product.Provider;
import net.sls.service.product.IProviderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class ProviderService implements IProviderService {

	@Autowired
	private IProviderComponent providerComponent;
	
	@Override
	public ResBo<Object> insertProvider(ReqBo reqBo) {
		Provider provider = reqBo.getReqModel(Provider.class);
		if(provider != null){
			
			//判断供应商是否存在
			boolean isExist = providerComponent.selectByName(provider.getProviderName());
			if(isExist)
				return new ResBo<Object>(31L);
			
			Date createTime = new Date(System.currentTimeMillis());
			provider.setCreateTime(createTime);
			providerComponent.insertProvider(provider);
			return new ResBo<Object>();
		}
		return new ResBo<Object>(1L);
	}

	@Override
	public void deleteProvider(ReqBo reqBo) {
		providerComponent.deleteProvider(reqBo.getParamLong("ProviderId"));
		
	}

	@Override
	public ResBo<Provider> updateProvider(ReqBo reqBo) {
		providerComponent.updateProvider(reqBo.getReqModel(Provider.class));
		return new ResBo<Provider>();
	}

	@Override
	public ResBo<Provider> selectProviderById(ReqBo reqBo) {
		Provider provider = providerComponent.selectByPrimaryKey(reqBo.getParamLong("providerId"));
		ResBo<Provider> resBo = new ResBo<Provider>(provider);
		return resBo;
	}

	@Override
	public ResBo<Pager<List<Provider>>> selectProviders(ReqBo reqBo) {		
		return new ResBo<Pager<List<Provider>>>(
				providerComponent.selectProviders(
						reqBo.getParamStr("providerName"), 
						reqBo.getParamStr("address"), 
						reqBo.getParamStr("tel"),
						reqBo.getParamStr("fax"), 
						reqBo.getParamStr("contactName"), 
						reqBo.getParamStr("isVerify"),
						reqBo.getParamStr("areaCode"),
						(Date)reqBo.getParam("createTime1"), 
						(Date)reqBo.getParam("createTime2"), 
						(Date)reqBo.getParam("modifyTime1"),
						(Date)reqBo.getParam("modifyTime2"), 
						reqBo.getParamInt("page"), 
						reqBo.getParamInt("rows")
				)
			);
	}
	
	@Override
	public ResBo<List<ComboxDto>> selectProviderList(ReqBo reqBo) {
		return new ResBo<List<ComboxDto>>(providerComponent.selectProviderList(reqBo.getParamStr("areaCode"),reqBo.getParamStr("name")));
	}

}
