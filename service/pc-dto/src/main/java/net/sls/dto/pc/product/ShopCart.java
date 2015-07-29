package net.sls.dto.pc.product;

import java.io.Serializable;

public class ShopCart implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;

    private String cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart == null ? null : cart.trim();
    }
}
