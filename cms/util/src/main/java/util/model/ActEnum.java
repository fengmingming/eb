package util.model;

import framework.exception.BusinessException;

public enum ActEnum {
	Coupon(10,"优惠券"),
	Groupon(30,"闪购"),
	FlashSale(25,"限时抢购");
	
	private int actType;
	private String name;
	private ActEnum(int actType,String name){
		this.actType = actType;
		this.name = name;
	}
	
	public int getActType(){
		return this.actType;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static ActEnum getActEnum(int actType){
		switch(actType){
		case 30: return ActEnum.Groupon;
		case 25: return ActEnum.FlashSale;
		case 10: return ActEnum.Coupon;
		default: throw new BusinessException("ActEnum is not support value="+actType);
		}
	}
}
