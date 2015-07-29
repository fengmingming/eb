package net.sls.dao.ext.product;

import java.util.Date;
import java.util.List;

import net.sls.dto.product.Category;

import org.apache.ibatis.annotations.Param;

public interface CategoryMapperExt {
	public List<Category> selectCategorysByFilter(@Param("name") String name,
			@Param("parentId") Long parentId,@Param("isUse") Long isUse,
			@Param("createTime1") Date createTime1,@Param("createTime2") Date createTime2,
			@Param("type") Long type);
	
	public long countCategorysByFilter(@Param("name") String name,
			@Param("parentId") Long parentId,@Param("isUse") Long isUse,
			@Param("createTime1") Date createTime1,@Param("createTime2") Date createTime2,
			@Param("type") Long type);

	public List<Category> selectCategoryByPId(Long fatherId);

	public Category selectCategoryCodeByPId(@Param("parentId") Long parentId);
	
	public String selectCategoryCodeById(Long id);
	
	public String selectCategoryName(Long id);
	
}
