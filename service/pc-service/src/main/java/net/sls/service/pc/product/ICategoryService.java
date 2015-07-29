package net.sls.service.pc.product;

import java.util.List;

import net.sls.dto.pc.product.Category;
import net.sls.dto.pc.product.GoodsCategory;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface ICategoryService {
	public ResBo<List<Category>> getCategoryList(ReqBo reqBo);
	
	/**@author wangshaohui
	 * @Description: TODO 根据商品id找到它的所属品类名
	 * @param goodsId
	 * @return ResBo<GoodsCategory>
	 * @date 2015年1月21日 下午3:29:42
	 */
	ResBo<GoodsCategory> selectCategoryNames(ReqBo reqBo);
}
