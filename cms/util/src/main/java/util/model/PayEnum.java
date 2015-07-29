package util.model;

import framework.exception.BusinessException;

public enum PayEnum {

	Aliapy(1,"支付宝"),
	Balance(2,"余额"),
	Balance2(3,"代购余额"),
	Wxpay(4,"微信");

	PayEnum(int id,String name){
		this.id = id;
		this.name = name;
	}
	
	private int id;
	
	private String name;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static PayEnum getPayEnum(int id){
		switch(id){
		case 1:return Aliapy;
		case 2:return Balance;
		case 3:return Balance2;
		case 4:return Wxpay;
		}
		throw new BusinessException("pay id is failed; success is 1 or 2 or 3 or 4");
	}
}
