package net.sls.component.product;

import java.util.List;

import net.sls.dto.product.GoodsCategory;
import framework.web.Pager;

/**
 * @author wsh 商品品类关系的组件接口
 *
 */
public interface IGoodsCategoryComponent {

	/**
	 * 新增商品品类关系信息
	 * 
	 * @param GoodsCategory
	 */
	public void insertGoodsCategory(GoodsCategory goodsCategory);

	/**
	 * 删除一个商品品类关系信息
	 * 
	 * @param id
	 */
	public void deleteGoodsCategory(long id);

	/**
	 * 批量删除商品品类关系信息
	 * 
	 * @param ids
	 */
	public void deleteGoodsCategorys(List<Long> ids);

	/**
	 * 修改商品品类关系信息
	 * 
	 * @param GoodsCategory
	 */
	public void updateGoodsCategory(GoodsCategory goodsCategory);

	/**
	 * 根据id查找商品品类关系信息
	 * 
	 * @param id
	 */
	public GoodsCategory selectByPrimaryKey(long id);

	/**@author wangshaohui
	 * @Description: 查找商品品类关系
	 * @param oneId 品类id
	 * @param twoId 品类id
	 * @param threeId 品类id
	 * @param goodsId 商品id
	 * @param start 开始页
	 * @param number 每页的记录数
	 * @return Pager<List<GoodsCategory>>
	 * @date 2014年12月9日 下午7:21:29
	 */
	public Pager<List<GoodsCategory>> selectGoodsCategorys(Long oneId,Long twoId,Long threeId,Long goodsId,Integer start, Integer number);

	/**
	 * @author wangguojun
	 * @description: 修改品类关系
	 * @param oneId
	 * @param twoId
	 * @param threeId
	 * @param goodsId void
	 * @date 2015年1月6日 下午4:39:02
	 */
	public void updateByGoodsIdSelective(Long oneId,Long twoId,
			Long threeId,Long[] goodsId);
	
}
