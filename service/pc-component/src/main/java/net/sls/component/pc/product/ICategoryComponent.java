package net.sls.component.pc.product;

import java.util.List;

import net.sls.dto.pc.product.Category;
import net.sls.dto.pc.product.GoodsCategory;

public interface ICategoryComponent {

	List<Category> selectCategoryList(Long parentId);
	
	String selectCategoryName(Long categoryId);

	GoodsCategory selectCategory(Long goodsId);

	Category selectCategoryById(Long id);
}
