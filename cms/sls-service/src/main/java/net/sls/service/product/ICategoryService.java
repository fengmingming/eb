package net.sls.service.product;

import java.util.List;
import java.util.Map;

import util.model.ComboxDto;
import net.sls.dto.product.Category;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 品类的服务层
 *
 */
public interface ICategoryService {

	/**
	 * 
	 * 新增品类
	 * @param Category Category品类信息
	 * 
	 * */
	 public ResBo<Object> insertCategory(ReqBo reqBo);
	 
	 
	 /**
	  * 
	  * 删除品类
	  * @param CategoryId CategoryId 品类id
	  * 
	  * */
	 public ResBo<Object> deleteCategory(ReqBo reqBo);
	 
	 /**
	  * 
	  * 修改Category品类信息
	  * @param Category 要修改的Category品类信息
	  * @return Category 修改后的Category品类信息
	  * */
	 public ResBo<Category> updateCategory(ReqBo reqBo);
		
		/**
		 * 
		 * 根据id查询用Category品类信息
		 * 
		 * */
	 public ResBo<Category> selectCategoryById(ReqBo reqBo);
	 
		/**
		 * 查询Category品类信息
		 * 
		 * */
	 public ResBo<Pager<List<Category>>> selectCategorys(ReqBo reqBo);


	 public ResBo<List<Map<String, Object>>> selectCategorysByPid(ReqBo reqBo);
	 
	 
	 public ResBo<List<ComboxDto>> selectCategoryComboxByPid(ReqBo reqBo);

	 
	 public ResBo<Category> selectCategoryCodeByPId(ReqBo setParam);
		
}
