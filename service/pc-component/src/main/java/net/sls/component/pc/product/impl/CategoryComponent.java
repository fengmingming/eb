package net.sls.component.pc.product.impl;

import java.util.List;

import net.sls.component.pc.product.ICategoryComponent;
import net.sls.dao.pc.product.PCCategoryMapper;
import net.sls.dto.pc.product.Category;
import net.sls.dto.pc.product.GoodsCategory;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryComponent implements ICategoryComponent{
	
	@Autowired
	private PCCategoryMapper categoryMapper;
	
	@Override
	public List<Category> selectCategoryList(Long parentId) {
		return categoryMapper.selectCategoryList(parentId);
	}

	@Override
	public String selectCategoryName(Long categoryId) {
		return categoryMapper.selectCategoryName(categoryId);
	}

	@Override
	public GoodsCategory selectCategory(Long goodsId) {
		return categoryMapper.selectCategory(goodsId);
	}
	
	@Override
	public Category selectCategoryById(@Param("id") Long id){
		return categoryMapper.selectCategoryById(id);
	}
}
