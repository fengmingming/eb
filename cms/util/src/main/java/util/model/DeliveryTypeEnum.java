package util.model;

import framework.exception.BusinessException;

public enum DeliveryTypeEnum {

	DeliveryType_1(1,"自提"),
	DeliveryType_2(2,"送货");
	
	private int type;
	private String name;
	
	DeliveryTypeEnum(int type,String name){
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public static DeliveryTypeEnum getDeliveryTypeEnum(int type){
		switch(type){
		case 1:return DeliveryType_1;
		case 2:return DeliveryType_2;
		}
		throw new BusinessException("DeliveryType is 1 or 2");
	}
}
