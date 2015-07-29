package net.sls.component.product;

import java.util.Date;
import java.util.List;

import net.sls.dto.product.Category;
import framework.web.Pager;

/**
 * @author wsh 品类的组件接口
 *
 */
public interface ICategoryComponent {

	/**
	 * 新增品类信息
	 * 
	 * @param Category
	 */
	public void insertCategory(Category category);

	/**
	 * 删除一个品类信息
	 * 
	 * @param id
	 */
	public void deleteCategory(long id);

	/**
	 * 批量删除品类信息
	 * 
	 * @param ids
	 */
	public void deleteCategorys(List<Long> ids);

	/**
	 * 修改品类信息
	 * 
	 * @param Category
	 */
	public void updateCategory(Category category);

	/**
	 * 根据id查找品类信息
	 * 
	 * @param id
	 */
	public Category selectByPrimaryKey(long id);

	/**
	 * 根据条件查询品类信息列表
	 * @param name 品类名
	 * @param parentId 父亲id
	 * @param isUse 是否启用
	 * @param createTime1 按时间查的起时间
	 * @param createTime2 按时间查的始时间
	 * @param type 类型
	 * @return Pager<List<Category>>
	 */
	public Pager<List<Category>> selectCategorys(String name, Long parentId, Long isUse,
			Date createTime1,Date createTime2,Long type);

	/**@author wangshaohui
	 * @Description: TODO 根据父id查找对就的子品类
	 * @param paramInt
	 * @return List<Category>
	 * @date 2014年12月18日 上午11:27:24
	 */
	public List<Category> selectCategoryByFatherId(Long fatherId);
	/**
	 * 获取code 通过parentId  获取最大的code
	 * @param parentId
	 * @return
	 */
	public Category selectCategoryCodeByPId(Long parentId);
	
	public String selectCategoryCodeById(Long id);
	
	public String selectCategoryNameById(Long id);

}
