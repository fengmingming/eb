package net.sls.dto.pc.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.model.IsUseCouponEnum;
import net.sls.dto.pc.act.UserCoupon;
import net.sls.dto.pc.shopcart.ProductDto;
import net.sls.dto.pc.shopcart.SettleGood;
/**
 * 结算时返回的对象，productList里放要结算的对象
 * flag：flag为flase时，代表库存不足，不能结算
 * flag为true时，可以进行结算
 *
 */
public class SettlementsDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<ProductDto> productList = new ArrayList<ProductDto>();
	
	private List<UserCoupon> canUseCouponList = new ArrayList<UserCoupon>();
	
	private Long userCouponId;
	
	private boolean flag = true;
	//商品总数量
	private int number;
	
	private BigDecimal amount = BigDecimal.ZERO;
	
	private BigDecimal payPrice = BigDecimal.ZERO;
	
	private BigDecimal discountPrice = BigDecimal.ZERO;
	
	
	private BigDecimal points = BigDecimal.ZERO;
	
	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

	public List<UserCoupon> getCanUseCouponList() {
		return canUseCouponList;
	}

	public void setCanUseCouponList(List<UserCoupon> canUseCouponList) {
		this.canUseCouponList = canUseCouponList;
	}

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public void build(){
		Map<UserCoupon, BigDecimal> couponSumMap = new HashMap<UserCoupon, BigDecimal>();
		Map<UserCoupon, List<ProductDto>> couponProductMap = new HashMap<UserCoupon, List<ProductDto>>();
		for(ProductDto pd:productList){
			if(pd.isStockout() && pd.isChecked()){
				this.flag = false;
			}
			if(pd.isChecked()){
				this.amount = this.amount.add(pd.getAmount());
				this.discountPrice = this.discountPrice.add(pd.getDiscountPrice());
				this.payPrice = this.payPrice.add(pd.getPayPrice());
				this.points = this.points.add(pd.getPoints());
				//计算可以使用优惠券的商品总额
				for(SettleGood sg : pd.getSettleGoods()){
					if(IsUseCouponEnum.IsUseCouponEnum_1.getCode() != sg.isUseCoupon().intValue()){
						continue;
					}
					BigDecimal sgPrice = sg.getPrice().multiply(BigDecimal.valueOf(sg.getNumber()))
							.multiply(BigDecimal.valueOf(pd.getNumber()));
					for(UserCoupon coupon : sg.getCanUseCouponList()){
						if(couponSumMap.containsKey(coupon)){
							couponSumMap.put(coupon, couponSumMap.get(coupon).add(sgPrice));
						}else{
							couponSumMap.put(coupon, sgPrice);
						}
						List<ProductDto> products = null;
						if(couponProductMap.containsKey(coupon)){
							products = couponProductMap.get(coupon);
							
						}else{
							products = new ArrayList<ProductDto>();
						}
						products.add(pd);
						couponProductMap.put(coupon, products);
					}
				}
			}
			int number = 0;
			for(SettleGood sg:pd.getSettleGoods()){
				number = number + sg.getNumber();
			}
			this.number = this.number + (number*pd.getNumber());
		}
		//计算优惠折扣总额以及订单可使用的优惠券
		boolean canUseCoupon = false;
		for(UserCoupon userCoupon : couponSumMap.keySet()){
			BigDecimal couponAmount = couponSumMap.get(userCoupon);
			if(couponAmount.intValue() >= userCoupon.getMinimum().intValue()){
				this.canUseCouponList.add(userCoupon);
				if(userCouponId != null && userCoupon.getId().intValue() == userCouponId.intValue()){
					BigDecimal discountByCoupon = BigDecimal.valueOf(userCoupon.getParValue());
					if(discountByCoupon.compareTo(this.payPrice) > 0){
						discountByCoupon = this.payPrice;
					}
					this.payPrice = this.payPrice.subtract(discountByCoupon);
					List<ProductDto> products = couponProductMap.get(userCoupon);
					for(int index = 0; index < products.size(); index++){
						ProductDto product = products.get(index);
						List<SettleGood> sgs = product.getSettleGoods();
						for(int i = 0; i < sgs.size(); i++){
							SettleGood sg = sgs.get(i);
							BigDecimal price = sg.getPrice();
							sg.setEffPay(price.subtract((price.multiply(discountByCoupon)).divide(couponAmount, 2)));
						}
					}
					canUseCoupon = true;
				}
			}
		}
		if(!canUseCoupon){
			userCouponId = null; //如果选择的优惠券不能使用，则应该取消选择的优惠券
		}
		if(this.canUseCouponList != null){
			//对优惠券按照生成时间降序排列
			Collections.sort(this.canUseCouponList, new Comparator<UserCoupon>(){
				@Override
				public int compare(UserCoupon uc1, UserCoupon uc2) {
					if(uc1.getGenDate().before(uc2.getGenDate())){
						return 1;
					}else if(uc1.getGenDate().after(uc2.getGenDate())){
						return -1;
					}
					return 0;
				}
			});
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(ProductDto pd:productList){
			sb.append(pd.toString());
		}
		return sb.toString();
	}
	
	public String toStringChecked(){
		StringBuilder sb = new StringBuilder("");
		for(ProductDto pd:productList){
			if(pd.isChecked()){
				sb.append(pd.toString());
			}
		}
		return sb.toString();
	}
	
	public String toStringNotChecked(){
		StringBuilder sb = new StringBuilder("");
		for(ProductDto pd:productList){
			if(!pd.isChecked()){
				sb.append(pd.toString());
			}
		}
		return sb.toString();
	}
}
