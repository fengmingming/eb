package util.model;

public enum OrderStateEnum {

	NORMAL(1,"正常"),CANCEL(2,"取消"),DELETE(127,"删除");
	
	private int code;
	private String name;
	
	OrderStateEnum(int code,String name){
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
