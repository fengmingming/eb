package util.model;

/**
 * 是否已提货
 * @author huzeyu 2015-02-06
 *
 */
public enum IsDeliveryEnum {
	IsDelivery_0(0,"未提货"),
	IsDelivery_1(1,"已提货");
	
	private int code;
	private String name;
	
	IsDeliveryEnum(int code,String name){
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
