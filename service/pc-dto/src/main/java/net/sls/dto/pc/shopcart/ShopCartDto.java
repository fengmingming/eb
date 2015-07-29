package net.sls.dto.pc.shopcart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 购物车模型
 *
 */
public class ShopCartDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 分隔符
	 */
	public static final String SEPARATOR_S = Cart.SEPARATOR_S;
	public static final String SEPARATOR_E = Cart.SEPARATOR_E;
	public static final String SEPARATOR_L = Cart.SEPARATOR_L;
	public static final String SEPARATOR_A = Cart.SEPARATOR_A;

	private List<Cart> cartList = new ArrayList<Cart>();
	
	private int number;
	
	public ShopCartDto(String cart){
		this.cartList = getCarts(cart);
	}
	
	public ShopCartDto(){
		
	}
	
	
	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	@Override
	public String toString() {
		String str="";
		for (Cart cart : cartList) {
			str = str + cart.toString();
		}
		return str;
	}
	
	/**
	 * 解析shopCart表里cart
	 * @param cart
	 * @return
	 */
	public static List<Cart> getCarts(String str){
		List<Cart> cartList = new ArrayList<Cart>();
		if (null == str || "".equals(str.trim())) {
			return cartList;
		}
		String[] cartStrs = str.split(SEPARATOR_E);
		for (String cartStr:cartStrs) {
			cartList.add(new Cart(cartStr));	
		}
		return cartList;
	}
	
	public ShopCartDto mergeCart(ShopCartDto shopCart){
		List<Cart> cookCarts = shopCart.getCartList();
		boolean isExist = false;
		for (int i = 0; i < cookCarts.size(); i++) {
			Cart cc = cookCarts.get(i).build();
			isExist = false;
			for (int j = 0; j < this.cartList.size(); j++) {
				Cart c = this.cartList.get(j);
				if (cc.getCartSku().equals(c.getCartSku())) {
					c.initBuild().setNumber(Math.max(c.getNumber(), cc.getNumber()));
					c.initBuild().setSelect(c.isSelect()||cc.isSelect());
					isExist = true;
					break;
				}
			}
			if(!isExist){
				this.cartList.add(cc);
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @param cart  购物车或是cookies
	 * @param shopCart  加入购物车的
	 * @return
	 */
	public ShopCartDto addCart(List<Cart> carts) {
		boolean isExist = false;
		for (int i = 0; i < carts.size(); i++) {
			isExist = false;
			Cart cc = carts.get(i).build();
			for (int j = 0; j < this.cartList.size(); j++) {
				Cart c = this.cartList.get(j);
				if (cc.getCartSku().equals(c.getCartSku())) {
					c.initBuild().setNumber(c.getNumber() + cc.getNumber());
					c.initBuild().setSelect(cc.isSelect());
					isExist = true;
					break;
				}
			}
			if(!isExist){
				this.cartList.add(cc);
			}
		}
		return this;
	}
	// 修改数量
	public ShopCartDto modifyNumber(Cart cart) {
		for (int i = 0; i < this.cartList.size(); i++) {
			if (cart.build().getCartSku().equals(this.cartList.get(i).getCartSku())) {
				this.cartList.get(i).initBuild().setNumber(cart.getNumber());
				break;
			}
		}
		return this;
	}
	
	public ShopCartDto deleteCart(List<Cart> carts){
		if(carts != null){
			List<Cart> removeCarts = new ArrayList<Cart>();
			for (int i = 0; i < carts.size(); i++) {
				for (int j = 0; j < this.cartList.size(); j++) {
					if (carts.get(i).build().getCartSku().equals(this.cartList.get(j).getCartSku())){
						removeCarts.add(this.cartList.get(j));
						break;
					}
				}
			}
			this.cartList.removeAll(removeCarts);
		}
		return this;
	}
	
	public ShopCartDto changeSelect(List<Cart> carts){
		if(carts != null){
			for(int i=0;i < carts.size();i++){
				for(int j=0 ;j < this.cartList.size(); j++){
					if(carts.get(i).build().getCartSku().equals(this.cartList.get(j).getCartSku())){
						this.cartList.get(j).initBuild().setSelect(carts.get(i).isSelect());
						break;
					}
				}
			}
		}
		return this;
	}
	
	public int getNumber(){
		if(this.number == 0&&this.cartList.size() > 0){
			for(Cart cart:this.cartList){
				this.number = this.number + cart.getNumber();
			}
		}
		return this.number;
	}
}
