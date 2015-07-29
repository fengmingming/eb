package net.sls.component.programManage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;
import net.sls.component.programManage.IInterfaseComponent;
import net.sls.dao.ext.programManage.InterfaseMapperExt;
import net.sls.dto.programManage.Interfase;

@Component
public class InterfaseComponent implements IInterfaseComponent{

	@Autowired
	private InterfaseMapperExt interfaseMapperExt;
	@Override
	public Pager<List<Interfase>> selectInterfacesList(String interfase,
			String remark, Integer start,Integer number) {
		
		List<Interfase> list = interfaseMapperExt.selectInterfacesList(interfase,remark,(start-1)*number, number);
		long count = interfaseMapperExt.countInterfacesByFilter(interfase, remark);
		Pager<List<Interfase>> pager = new Pager<List<Interfase>>(list,count);
		return pager;
	}
	@Override
	public Interfase selectInterfacesInfo(Long id) {
		return interfaseMapperExt.selectInterfacesInfo(id);
	}
	@Override
	public Pager<List<Interfase>> selectInterfaces(String url,String interfase, Integer start,
			Integer maxValue) {
		List<Interfase> list = interfaseMapperExt.selectInterfaces(url,interfase,(start-1)*maxValue, maxValue);
		long count = interfaseMapperExt.countInterfaceByFilter(url,interfase);
		Pager<List<Interfase>> pager = new Pager<List<Interfase>>(list,count);
		return pager;
	}
	@Override
	public Interfase insertInterfaces(Interfase interfase) {
		int i = interfaseMapperExt.insertInterfaseReturnId(interfase);
		if(i!=1){
			throw new BusinessException(1L);
		}
		return interfase;
	}
	@Override
	public Interfase updateInterfaces(Interfase interfase) {
		int i = interfaseMapperExt.updateByPrimaryKey(interfase);
		if(i!=1){
			throw new BusinessException(2L);
		}
		return interfase;
	}

}
