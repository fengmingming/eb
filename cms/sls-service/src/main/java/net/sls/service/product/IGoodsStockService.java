package net.sls.service.product;

import java.util.List;
import java.util.Map;

import net.sls.dto.product.GoodsStock;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 商品库存的服务层
 *
 */
public interface IGoodsStockService {

	 /**
	  * 
	  * 修改GoodsStock商品库存信息
	  * @param GoodsStock 要修改的GoodsStock商品库存信息
	  * @return GoodsStock 修改后的GoodsStock商品库存信息
	  * */
	 public ResBo<GoodsStock> updateGoodsStock(ReqBo reqBo);
	 
	 public ResBo<Pager<List<Map<String,Object>>>> selectGoodsStocks(ReqBo reqBo);
}
