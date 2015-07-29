package net.sls.component.product.impl;

import java.util.Date;
import java.util.List;

import net.sls.component.product.ICategoryComponent;
import net.sls.dao.ext.product.CategoryMapperExt;
import net.sls.dao.product.CategoryMapper;
import net.sls.dto.product.Category;
import net.sls.dto.product.CategoryExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.BusinessExceptionUtil;
import util.model.CategoryEnum;
import framework.exception.BusinessException;
import framework.web.Pager;

/**
 * 品类组件的实现类
 *
 */
@Component
public class CategoryComponent implements ICategoryComponent {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private CategoryMapperExt categoryMapperExt;
	
	@Override
	public void insertCategory(Category category) {
		int i = categoryMapper.insertSelective(category);
		if(i != 1){
			throw new BusinessException(1L);
		}
		
	}

	@Override
	public void updateCategory(Category category) {
		int i = categoryMapper.updateByPrimaryKeySelective(category);
		if(i != 1){
			throw new BusinessException(2L);
		}
		
	}

	@Override
	public Category selectByPrimaryKey(long id) {
		CategoryExample c = new CategoryExample();
		c.createCriteria().andIdEqualTo(id);
		List<Category> categorys = categoryMapper.selectByExample(c);
		if(categorys.size() > 1){
			throw BusinessExceptionUtil.createBusinessException(5L, id);
		}
		if(categorys.size() == 1){
			return categorys.get(0);
		}
		return null;
	}

	@Override
	public void deleteCategory(long id) {
		int i = categoryMapper.deleteByPrimaryKey(id);
		if(i != 1){
			throw new BusinessException(3L);
		}
	}

	@Override
	public void deleteCategorys(List<Long> ids) {
		CategoryExample c = new CategoryExample();
		c.createCriteria().andIdIn(ids);
		int i = categoryMapper.deleteByExample(c);
		if(i == 0){
			throw new BusinessException(3L);
		}
		
	}

	@Override
	public Pager<List<Category>> selectCategorys(String name, Long parentId, Long isUse,
			Date createTime1,Date createTime2,Long type){
	
		long count = categoryMapperExt.countCategorysByFilter(name, parentId, isUse, createTime1, createTime2, type);
		List<Category> list = categoryMapperExt.selectCategorysByFilter(name, parentId, isUse, createTime1, createTime2, type);
		Pager<List<Category>> pager = new Pager<List<Category>>(list,count);
		return pager;
	}

	@Override
	public List<Category> selectCategoryByFatherId(Long fatherId) {
		return categoryMapperExt.selectCategoryByPId(fatherId);
	}

	@Override
	public Category selectCategoryCodeByPId(Long parentId) {
		return categoryMapperExt.selectCategoryCodeByPId(parentId);
	}

	@Override
	public String selectCategoryCodeById(Long id) {
		if(id == null){
			return null;
		}
		String code = CategoryEnum.getCategoryCode(id);
		if(code == null){
			code = categoryMapperExt.selectCategoryCodeById(id);
			if(code != null){
				CategoryEnum.setCategoryCode(id, code);
			}
		}
		return code;
	}
	
	@Override
	public String selectCategoryNameById(Long id) {
		if(id == null){
			return null;
		}
		String name = CategoryEnum.getCategoryName(id);
		if(name == null){
			name = categoryMapperExt.selectCategoryName(id);
			if(name != null){
				CategoryEnum.setCategoryName(id, name);
			}
		}
		return name;
	}

}
