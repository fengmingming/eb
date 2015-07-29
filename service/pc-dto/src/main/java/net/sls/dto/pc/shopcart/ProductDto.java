package net.sls.dto.pc.shopcart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sls.dto.pc.act.AbstractHandler;

public class ProductDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String SEPARATOR_S = Cart.SEPARATOR_S;
	public static final String SEPARATOR_E = Cart.SEPARATOR_E;
	public static final String SEPARATOR_L = Cart.SEPARATOR_L;
	public static final String SEPARATOR_A = Cart.SEPARATOR_A;
	public static final String SEPARATOR_N = Cart.SEPARATOR_N;

	/**
	 * 或是打包出售de
	 */
	private List<SettleGood> settleGoods = new ArrayList<SettleGood>();
	
	private int number = 1;
	
	private BigDecimal amount = BigDecimal.ZERO;

	private BigDecimal payPrice = BigDecimal.ZERO;

	private BigDecimal discountPrice = BigDecimal.ZERO;

	private BigDecimal points = BigDecimal.ZERO;
	
	private BigDecimal marketParice = BigDecimal.ZERO;
	
	private BigDecimal price = BigDecimal.ZERO;

	private List<AbstractHandler> actHandlers = new ArrayList<AbstractHandler>();

	private boolean isStockout = false;
	// 是否选择此商品
	private boolean checked = true;
	
	private String productSku;
	
	private boolean isBuild;
	
	private String remark;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		verifyBuild();
		this.number = number;
	}

	public BigDecimal getAmount() {
		build();
		return amount;
	}

	public boolean isChecked() {
		build();
		return checked;
	}

	public void setChecked(boolean checked) {
		verifyBuild();
		this.checked = checked;
	}

	public boolean isStockout() {
		build();
		return isStockout;
	}

	public void setStockout(boolean isStockout) {
		verifyBuild();
		this.isStockout = isStockout;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public List<AbstractHandler> getActHandlers() {
		return actHandlers;
	}

	public void setActHandlers(List<AbstractHandler> actHandlers) {
		verifyBuild();
		this.actHandlers = actHandlers;
	}

	public List<SettleGood> getSettleGoods() {
		return settleGoods;
	}

	public void setSettleGoods(List<SettleGood> settleGoods) {
		verifyBuild();
		this.settleGoods = settleGoods;
	}

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public String getProductSku() {
		build();
		return productSku;
	}
	
	/**
	 * 
	 * 此方法计算总额，productSku,单价，市场价，是否缺货，是否勾选
	 * */
	public ProductDto build() {
		if(!isBuild&&this.settleGoods.size()>0){
			Collections.sort(this.settleGoods, new Comparator<SettleGood>() {

				public int compare(SettleGood sg1, SettleGood sg2) {
					if (sg1.getId() > sg2.getId()) {
						return 1;
					} else if (sg1.getId() < sg2.getId()) {
						return -1;
					}
					return 0;
				}
			});
			Collections.sort(this.actHandlers, new Comparator<AbstractHandler>() {

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
			sb.append(SEPARATOR_L);
			sb.append(this.number);
			sb.append(SEPARATOR_L);
			SSettleGood sg = null;
			for (int i = 0, j = this.settleGoods.size(); i < j; i++) {
				sg = (SSettleGood) this.settleGoods.get(i);
				this.price = this.price.add(sg.getPrice().multiply(BigDecimal.valueOf(sg.getNumber())));
				this.marketParice = this.marketParice.add(sg.getMarketPrice().multiply(BigDecimal.valueOf(sg.getNumber())));
				this.amount = this.amount.add(sg.getPrice().multiply(BigDecimal.valueOf(sg.getNumber())).multiply(BigDecimal.valueOf(this.number)));
				if ((sg.getNumber() * this.number > sg.getMinNumber())) {
					this.isStockout = true;
					this.checked = false;
					this.remark = "还剩"+sg.getMinNumber()+"件";
				}
				if(sg.is_isJudgeAreaStcok() && sg.getLimittype() != 0 && sg.get_goodAreaId() == null){
					this.isStockout = true;
					this.checked = false;
					this.remark = "您所在地区无法购买";
				}
				this.checked = this.checked&&sg.is_isSelect();
				sb.append(sg.getId());
				sb.append(SEPARATOR_N);
				sb.append(sg.getNumber());
				if (i < j - 1) {
					sb.append(SEPARATOR_S);
				}
			}
			if(this.actHandlers.size() > 0){
				sb.append(SEPARATOR_L);
				AbstractHandler handler = null;
				for(int i = 0,j = this.actHandlers.size();i < j;i++){
					handler = this.actHandlers.get(i);
					sb.append(handler.getActType());
					sb.append(SEPARATOR_A);
					sb.append(handler.getActId());
					if(i < j - 1){
						sb.append(SEPARATOR_S);
					}
				}
			}
			sb.append(SEPARATOR_E);
			this.productSku = this.checked ?"Y"+sb.toString():"N"+sb.toString();
			isBuild = true;
		}
		
		return this;
	}

	@Override
	public String toString(){
		build();
		return this.productSku == null?"":this.productSku;
	}
	
	@Override
	public int hashCode() {
		build();
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productSku == null) ? 0 : productSku.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		build();
		if(this.productSku == null){
			return false;
		}
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDto other = (ProductDto) obj;
		String uniqueSkuT = this.productSku.split(SEPARATOR_L,3)[2];
		String uniqueSku = other.build().getProductSku();
		if(uniqueSku!=null){
			uniqueSku = uniqueSku.split(SEPARATOR_L,3)[2]; 
		}else{
			return false;
		}
		if (!uniqueSkuT.equals(uniqueSku))
			return false;
		return true;
	}
	
	public ProductDto initBuild(){
		isBuild = false;
		return this;
	}
	
	private void verifyBuild(){
		if(this.isBuild){
			throw new RuntimeException("cart has builded");
		}
	}
	
	public BigDecimal getMarketParice() {
		return marketParice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
