package net.sls.component.product.impl;

import java.util.Date;
import java.util.List;

import net.sls.component.product.IProviderComponent;
import net.sls.dao.ext.product.ProviderMapperExt;
import net.sls.dao.product.ProviderMapper;
import net.sls.dto.product.Provider;
import net.sls.dto.product.ProviderExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.BusinessExceptionUtil;
import util.model.ComboxDto;
import framework.exception.BusinessException;
import framework.web.Pager;

/**
 * 供应商组件的实现类
 *
 */
@Component
public class ProviderComponent implements IProviderComponent {

	@Autowired
	private ProviderMapper providerMapper;
	
	@Autowired
	private ProviderMapperExt providerMapperExt;
	
	@Override
	public void insertProvider(Provider provider) {
		int i = providerMapper.insertSelective(provider);
		if(i != 1){
			throw new BusinessException(1L);
		}
		
	}

	@Override
	public void updateProvider(Provider provider) {
		int i = providerMapper.updateByPrimaryKeySelective(provider);
		if(i != 1){
			throw new BusinessException(2L);
		}
		
	}

	@Override
	public Provider selectByPrimaryKey(long id) {
		ProviderExample p = new ProviderExample();
		p.createCriteria().andIdEqualTo(id);
		List<Provider> providers = providerMapper.selectByExample(p);
		if(providers.size() > 1){
			throw BusinessExceptionUtil.createBusinessException(5L, id);
		}
		if(providers.size() == 1){
			return providers.get(0);
		}
		return null;
	}

	@Override
	public void deleteProvider(long id) {
		int i = providerMapper.deleteByPrimaryKey(id);
		if(i != 1){
			throw new BusinessException(3L);
		}
	}

	@Override
	public void deleteProviders(List<Long> ids) {
		ProviderExample p = new ProviderExample();
		p.createCriteria().andIdIn(ids);
		int i = providerMapper.deleteByExample(p);
		if(i == 0){
			throw new BusinessException(3L);
		}
		
	}

	@Override
	public Pager<List<Provider>> selectProviders(String providerName, String address, String tel,
			String fax,String contactName,String isVerify,String areaCode,Date createTime1,Date createTime2,
			Date modifyTime1,Date modifyTime2,Integer start, Integer number) {
	
		long count = providerMapperExt.countProvidersByFilter(providerName, address, tel, fax, contactName, isVerify,areaCode, createTime1, createTime2, modifyTime1, modifyTime2);
		List<Provider> list = providerMapperExt.selectProvidersByFilter(providerName, address, tel, fax, contactName, isVerify,areaCode, createTime1, createTime2, modifyTime1, modifyTime2, (start-1)*number, number);
		Pager<List<Provider>> pager = new Pager<List<Provider>>(list,count);
		return pager;
	}

	@Override
	public List<ComboxDto> selectProviderList(String areaCode,String name) {
		List<ComboxDto> list = providerMapperExt.selectProviderList(areaCode,name);
		return list;
	}

	@Override
	public boolean selectByName(String providerName) {
		int i = providerMapperExt.selectByName(providerName);
		return i>0?true:false;
	}
	
}
