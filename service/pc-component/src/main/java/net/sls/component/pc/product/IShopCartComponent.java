package net.sls.component.pc.product;

import net.sls.dto.pc.product.ShopCart;

public interface IShopCartComponent {

	ShopCart insertShopCart(ShopCart shopCart);

	ShopCart selectShopCart(Long userId);

	ShopCart updateShopCart(ShopCart shopCart);
}
