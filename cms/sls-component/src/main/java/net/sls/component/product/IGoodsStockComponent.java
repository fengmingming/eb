package net.sls.component.product;

import java.util.List;
import java.util.Map;

import net.sls.dto.product.GoodsStock;
import framework.web.Pager;

/**
 * @author wsh 商品库存的组件接口
 *
 */
public interface IGoodsStockComponent {

	/**
	 * 修改商品库存信息
	 * 
	 * @param GoodsStock
	 */
	public void updateGoodsStock(GoodsStock goodsStock);

	
	public Pager<List<Map<String,Object>>> selectGoodsStocks(String areaCode,Long goodsId,String goodsName,String sku,Integer providerId,Integer number,Integer number2,int page,int rows);

}
