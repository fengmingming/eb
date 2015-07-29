package net.sls.service.pc.product;

import net.sls.dto.pc.product.SettlementsDto;
import net.sls.dto.pc.product.ShopCart;
import net.sls.dto.pc.shopcart.ShopCartDto;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IShopCartService {
	/**
	 * 登录后加入购物车
	 * @param reqBo
	 * @return
	 */
	public ResBo<ShopCart> insertShopCart(ReqBo reqBo);
	/**
	 * 未登录登陆后合并购物车
	 */
	public ResBo<ShopCartDto> merge(ReqBo reqBo);
	
	/**
	 * 删除购物车
	 */
	public ResBo<ShopCartDto> delete(ReqBo reqBo);
	
	/**
	 * 查询购物车
	 */
	public ResBo<ShopCartDto> selectShopCart(ReqBo reqBo);
	/**
	 * 购物车数量修改
	 * @param reqBo
	 * @return
	 */
	public ResBo<ShopCartDto> changeNumberGoodsCartDto(ReqBo reqBo);
	/**
	 * @param reqBo
	 * @return
	 */
	public ResBo<SettlementsDto> settleShopCart(ReqBo reqBo);
	/**
	 * 获取购物车选中或未选中状态
	 * @param reqBo
	 * @return
	 */
	public ResBo<ShopCartDto> changeState(ReqBo reqBo);
	
	/**
	 * 得到购物车数量
	 * */
	public ResBo<Integer> getShopCartCount(ReqBo reqBo);
	
}
