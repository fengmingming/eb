package net.sls.dao.pc.product;

import java.util.List;

import net.sls.dto.pc.product.Category;
import net.sls.dto.pc.product.GoodsCategory;

import org.apache.ibatis.annotations.Param;

public interface PCCategoryMapper {

	List<Category> selectCategoryList(@Param("parentId") Long parentId);

	/**@author wangshaohui
	 * @Description: TODO 根据商品id查找它所属的品类
	 * @param goodsId
	 * @return GoodsCategory
	 * @date 2015年1月21日 下午2:58:54
	 */
	GoodsCategory selectCategory(@Param("goodsId") Long goodsId);
	
	/**@author wangshaohui
	 * @Description: TODO 根据品类id找到品类的名字
	 * @param categoryId
	 * @return String
	 * @date 2015年1月21日 下午3:07:30
	 */
	String selectCategoryName(@Param("categoryId") Long categoryId);
	
	/**
	 * 根据品类id找到品类
	 * @param id
	 * @return
	 */
	Category selectCategoryById(@Param("id") Long id);
}
