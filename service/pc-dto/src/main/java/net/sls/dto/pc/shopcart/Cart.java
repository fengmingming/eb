package net.sls.dto.pc.shopcart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sls.dto.pc.act.AbstractHandler;

public class Cart implements Serializable {

	/**
	 * Y_number_id~N#id~N#id~N_actType$actId#actType@actId#actType@actId&Y_number_id~N_actType@actId&
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 分隔符
	 */
	public static final String SEPARATOR_S = "#";
	public static final String SEPARATOR_L = "_";
	public static final String SEPARATOR_A = "@";
	public static final String SEPARATOR_N = "~";
	public static final String SEPARATOR_E = "&";
	/**
	 * 是否选择
	 */
	private boolean isSelect = true;
	/**
	 * 商品Id 或是打包出售的id
	 */
	private List<CartGood> cartGoods = new ArrayList<CartGood>();

	/**
	 * 
	 * 唯一标识cart
	 * 
	 * */
	private String cartSku;
	/**
	 * 购买数量
	 */
	private int number = 1;
	
	List<AbstractHandler> actHandlers = new ArrayList<AbstractHandler>();
	
	public List<AbstractHandler> getActHandlers() {
		return actHandlers;
	}

	private boolean isBuild = false;

	public Cart() {

	}

	//Y_number_id~N#id~N#id~N_actType$actId#actType$actId#actType$actId
	public Cart(String cartStr) {
		if (cartStr != null && !"".equals(cartStr)) {
			if (cartStr.endsWith(SEPARATOR_E)) {
				cartStr = cartStr.substring(0, cartStr.length() - 1);
			}
			String[] cgs = cartStr.split(SEPARATOR_L);
			this.isSelect = "Y".equals(cgs[0]) ? true : false;
			this.number = Integer.parseInt(cgs[1]);
			String[] cg = null;
			for (String s : cgs[2].split(SEPARATOR_S)) {
				cg = s.split(SEPARATOR_N);
				this.cartGoods.add(new CCartGood(Long.parseLong(cg[0]),Integer.parseInt(cg[1])).set_isSelect(this.isSelect).set_number(this.number));
			}
			if(cgs.length > 3){
				String [] act = null;
				AbstractHandler handler = null;
				String[] acts = cgs[3].split(SEPARATOR_S);
				for(String s:acts){
					act = s.split(SEPARATOR_A);
					handler = new AbstractHandler();
					handler.setActType(Integer.parseInt(act[0]));
					handler.setActId(Integer.parseInt(act[1]));
					this.actHandlers.add(handler);
				}
			}
		}
	}

	public int getNumber() {
		return number;
	}

	public Cart setNumber(int number) {
		verifyBuild();
		this.number = number;
		return this;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public Cart setSelect(boolean isSelect) {
		verifyBuild();
		this.isSelect = isSelect;
		return this;
	}

	public List<CartGood> getCartGoods() {
		return cartGoods;
	}

	public Cart setCartGoods(List<CartGood> cartGoods) {
		verifyBuild();
		this.cartGoods = cartGoods;
		return this;
	}
	
	public Cart addCartGood(long gid){
		verifyBuild();
		this.cartGoods.add(new CartGood(gid));
		return this;
	}

	public Cart addCartGood(long gid, int cartNumber) {
		verifyBuild();
		this.cartGoods.add(new CartGood(gid));
		this.number = cartNumber;
		return this;
	}
	
	public String getCartSku() {
		build();
		return cartSku;
	}

	/**
	 * 用来生成cartSku 完成id排序
	 * */
	public Cart build() {
		if (!this.isBuild&&this.cartGoods.size() > 0) {
			Collections.sort(this.cartGoods, new Comparator<CartGood>() {

				@Override
				public int compare(CartGood cg1, CartGood cg2) {
					if (cg1.getId() > cg2.getId()) {
						return 1;
					} else if (cg1.getId() < cg2.getId()) {
						return -1;
					}
					return 0;
				}
			});
			Collections.sort(this.actHandlers, new Comparator<AbstractHandler>() {

				@Override
				public int compare(AbstractHandler a1, AbstractHandler a2) {
					if (a1.getActType() > a2.getActType()) {
						return 1;
					} else if (a1.getActType() < a2.getActType()) {
						return -1;
					}
					return 0;
				}
			});
			StringBuilder sb = new StringBuilder();
			CartGood cg = null;
			for (int i = 0, j = this.cartGoods.size(); i < j; i++) {
				cg = this.cartGoods.get(i);
				sb.append(cg.getId());
				sb.append(SEPARATOR_N);
				sb.append(cg.getNumber());
				if (i < j - 1) {
					sb.append(SEPARATOR_S);
				}
			}
			//不使用活动信息
//			if(this.actHandlers.size() > 0){
//				sb.append(SEPARATOR_L);
//				AbstractHandler handler = null;
//				for(int i = 0,j = this.actHandlers.size();i < j;i++){
//					handler = this.actHandlers.get(i);
//					sb.append(handler.getActType());
//					sb.append(SEPARATOR_A);
//					sb.append(handler.getActId());
//					if(i < j - 1){
//						sb.append(SEPARATOR_S);
//					}
//				}
//			}
			sb.append(SEPARATOR_E);
			this.cartSku = sb.toString();
			this.isBuild = true;
		}
		return this;
	}

	@Override
	public int hashCode() {
		build();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartSku == null) ? 0 : cartSku.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		build();
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartSku == null) {
			if (other.cartSku != null)
				return false;
		} else if (!cartSku.equals(other.cartSku))
			return false;
		return true;
	}

	@Override
	public String toString() {
		build();
		if (cartSku == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(isSelect ? "Y" : "N");
		sb.append(SEPARATOR_L);
		sb.append(this.number);
		sb.append(SEPARATOR_L);
		sb.append(cartSku);
		return sb.toString();
	}

	public Cart initBuild(){
		isBuild = false;
		return this;
	}
	
	private void verifyBuild(){
		if(this.isBuild){
			throw new RuntimeException("cart has builded");
		}
	}
}