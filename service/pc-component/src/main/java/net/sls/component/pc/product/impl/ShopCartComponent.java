package net.sls.component.pc.product.impl;

import net.sls.component.pc.product.IShopCartComponent;
import net.sls.dao.pc.product.PCShopCartMapper;
import net.sls.dto.pc.product.ShopCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
@Component
public class ShopCartComponent implements IShopCartComponent{
	
	@Autowired
	private PCShopCartMapper shopCartMapper;

	@Override
	public ShopCart insertShopCart(ShopCart shopCart) {
		int i = shopCartMapper.insertShopCart(shopCart);
		if(i != 1){
			throw new BusinessException(8L);
		}
		return shopCart;
	}

	@Override
	public ShopCart selectShopCart(Long userId) {
		ShopCart shopCart=new ShopCart();
		shopCart=shopCartMapper.selectShopCart(userId);
		return shopCart;
	}

	@Override
	public ShopCart updateShopCart(ShopCart shopCart) {
		int i = shopCartMapper.updateShopCartByUserId(shopCart);
		if(i != 1){
			throw new BusinessException(9L);
		}
		return shopCart;
	}
}
