package util.model;

public enum IsPaidEnum {

	IsPaid_1(1,"未付款"),
	IsPaid_2(2,"已付款");
	
	private int code;
	private String name;
	
	IsPaidEnum(int code,String name){
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
