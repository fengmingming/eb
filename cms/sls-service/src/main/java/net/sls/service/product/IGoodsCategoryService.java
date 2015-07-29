package net.sls.service.product;

import java.util.List;

import net.sls.dto.product.GoodsCategory;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 商品品类关系的服务层
 *
 */
public interface IGoodsCategoryService {

	/**
	 * 
	 * 新增商品品类关系
	 * @param GoodsCategory GoodsCategory商品品类关系信息
	 * 
	 * */
	 public ResBo<Object> insertGoodsCategory(ReqBo reqBo);
	 
	 
	 /**
	  * 
	  * 删除商品品类关系
	  * @param GoodsCategoryId GoodsCategoryId 商品品类关系id
	  * 
	  * */
	 public void deleteGoodsCategory(ReqBo reqBo);
	 
	 /**
	  * 
	  * 修改GoodsCategory商品品类关系信息
	  * @param GoodsCategory 要修改的GoodsCategory商品品类关系信息
	  * @return GoodsCategory 修改后的GoodsCategory商品品类关系信息
	  * */
	 public ResBo<GoodsCategory> updateGoodsCategory(ReqBo reqBo);
		
		/**
		 * 
		 * 根据id查询用GoodsCategory商品品类关系信息
		 * 
		 * */
	 public ResBo<GoodsCategory> selectGoodsCategoryById(ReqBo reqBo);
	 
		/**
		 * 查询GoodsCategory商品品类关系信息
		 * 
		 * */
	 public ResBo<Pager<List<GoodsCategory>>> selectGoodsCategorys(ReqBo reqBo);
		
		
	 public ResBo<Object> updateByGoodsIdSelective(ReqBo reqBo);
	 
}
