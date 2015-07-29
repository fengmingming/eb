package util.model;

public enum IsUseCouponEnum {
	IsUseCouponEnum_0(0,"不可以使用优惠券"),
	IsUseCouponEnum_1(1,"可以使用优惠劵");
	
	private int code;
	private String name;
	
	IsUseCouponEnum(int code,String name){
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
