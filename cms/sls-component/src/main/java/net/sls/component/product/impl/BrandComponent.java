package net.sls.component.product.impl;

import java.util.Date;
import java.util.List;

import net.sls.component.product.IBrandComponent;
import net.sls.dao.ext.product.BrandMapperExt;
import net.sls.dao.product.BrandMapper;
import net.sls.dto.product.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.model.ComboxDto;
import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class BrandComponent implements IBrandComponent{

	
	@Autowired
	private BrandMapper brandMapper;
	
	@Autowired
	private BrandMapperExt brandMapperExt;
	
	@Override
	public void insertSelective(Brand record) {

		int i = brandMapper.insertSelective(record);
		if(i != 1){
			throw new BusinessException(1L);
		}
		
	}

	@Override
	public void updateByPrimaryKeySelective(Brand record) {

		int i = brandMapper.updateByPrimaryKeySelective(record);
		if(i != 1){
			throw new BusinessException(2L);
		}
		
	}

	@Override
	public Pager<List<Brand>> selectBySelective(Long id, String name, String enName, String description, 
			String spell, String keyword, String url, String img, Date createTime, 
			Integer start, Integer number) {

		Long count = brandMapperExt.countBySelective(id, name, enName, description, spell, keyword, url, img, createTime);
		List<Brand> list = brandMapperExt.selectBySelective(id, name, enName, description, spell, keyword, url, img, createTime, start-1, number);
		
		return new Pager<List<Brand>>(list,count);
	}

	@Override
	public List<ComboxDto> selectBrandCombobox() {
		return brandMapperExt.selectBrandCombobox();
	}

	@Override
	public boolean selectNameIsExist(String name) {
		int num = brandMapperExt.countByName(name);
		if(num>=1){
			return true;
		}
		return false;
	}

	
	
}
