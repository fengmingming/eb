package net.sls.component.dict.impl;

import java.util.List;

import net.sls.component.dict.IDictComponent;
import net.sls.dao.ext.dict.DictMapperExt;
import net.sls.dao.product.DictMapper;
import net.sls.dto.product.Dict;
import net.sls.dto.product.DictExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.BusinessExceptionUtil;
import util.model.ComboxDto;
import framework.exception.BusinessException;
import framework.web.Pager;

/**
 * 字典组件的实现类
 *
 */
@Component
public class DictComponent implements IDictComponent{

	@Autowired
	private DictMapper dictMapper;
	
	@Autowired
	private DictMapperExt dictMapperExt;
	
	@Override
	public void insertDict(Dict dict) {
		int i = dictMapper.insertSelective(dict);
		if(i != 1){
			throw new BusinessException(1L);
		}
		
	}

	@Override
	public void updateDict(Dict dict) {
		int i = dictMapper.updateByPrimaryKeySelective(dict);
		if(i != 1){
			throw new BusinessException(2L);
		}
		
	}

	@Override
	public Dict selectDict(long id) {
		DictExample d = new DictExample();
		d.createCriteria().andIdEqualTo(id);
		List<Dict> dicts = dictMapper.selectByExample(d);
		if(dicts.size() > 1){
			throw BusinessExceptionUtil.createBusinessException(5L, id);
		}
		if(dicts.size() == 1){
			return dicts.get(0);
		}
		return null;
	}

	@Override
	public List<Dict> selectDict(String name) {
		DictExample d = new DictExample();
		d.createCriteria().andNameEqualTo(name);
		List<Dict> dicts = dictMapper.selectByExample(d);
		
		return dicts;
	}

	@Override
	public void deleteDict(long id) {
		int i = dictMapper.deleteByPrimaryKey(id);
		if(i != 1){
			throw new BusinessException(3L);
		}
	}

	@Override
	public void deleteDicts(List<Long> ids) {
		DictExample d = new DictExample();
		d.createCriteria().andIdIn(ids);
		int i = dictMapper.deleteByExample(d);
		if(i == 0){
			throw new BusinessException(3L);
		}
		
	}

	@Override
	public Pager<List<Dict>> selectDicts(String code, String name, Integer type,
			Integer start, Integer number) {
		long count = dictMapperExt.countDictsByFilter(code, name, type);
		List<Dict> list = dictMapperExt.selectDictsByFilter(code, name, type, start, number);
		Pager<List<Dict>> pager = new Pager<List<Dict>>(list,count);
		return pager;
	}

	@Override
	public List<ComboxDto> selectDictCombox() {
		return dictMapperExt.selectDictCombox();
	}

	@Override
	public List<ComboxDto> selectDictComboxByType(Integer type, String key) {
		return dictMapperExt.selectDictComboxByType(type, key);
	}
	
}
