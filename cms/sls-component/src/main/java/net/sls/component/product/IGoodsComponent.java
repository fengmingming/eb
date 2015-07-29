package net.sls.component.product;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sls.dto.ext.product.GoodsAddBeanExt;
import net.sls.dto.product.Goods;
import util.model.ComboxDto;
import framework.web.Pager;

public interface IGoodsComponent {

	/**
	 * @author wangguojun
	 * @Description: TODO
	 * @param goods
	 *            void
	 * @date 2014年12月3日 下午3:01:23
	 */

	public void insertGoods(GoodsAddBeanExt ext);
	
	public void updateAll(GoodsAddBeanExt ext);

	/**
	 * @author wangguojun
	 * @description: 按id查找商品
	 * @param goodId
	 * @return Goods
	 * @date 2014年12月9日 下午2:58:56
	 */
	public Goods selectByPrimaryKey(Long goodId);
	
	public List<ComboxDto> selectGoodsListByProvider(Long providerId);
	
	/**@author wangshaohui
	 * @Description: TODO 根据品类id查找goods列表，不包括详情
	 * @param oneId
	 * @param twoId
	 * @param threeId
	 * @param start
	 * @param number
	 * @return Pager<List<Goods>>
	 * @date 2014年12月23日 下午6:03:50
	 */
	public Pager<List<Goods>> selectGoodsByCategoryId(String areaCode,Long providerId,Long oneId,Long twoId,Long threeId,Integer start,Integer number);
	
	
	public Pager<List<Map<String,Object>>> selectGoodsByFilter(String areaCode,Long goodsId,String sku,String name,BigDecimal price,BigDecimal price2, Integer provider,Integer isSale, int start,int number);
	
	public void updateGoodsById(Goods g);
	
	public Map<String,Object> selectUpdateGoodsInfo(long goodsId);
	
	public void updateBatchGoodsIsSale(List<Long> ids,int isSale);
	
	public void updateFlushBuyNum();
	
	public void updateBatchUseCoupon(List<Long> ids, boolean isUse);
}
