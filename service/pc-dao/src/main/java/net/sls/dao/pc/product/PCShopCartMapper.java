package net.sls.dao.pc.product;

import net.sls.dto.pc.product.ShopCart;

public interface PCShopCartMapper {

	int insertShopCart(ShopCart shopCart);

	ShopCart selectShopCart(Long userId);

	int updateShopCartByUserId(ShopCart shopCart);


}
