package util.model;

public enum AmountTypeEnum {

	NORMAL(1,"正常"),FREEZE(2,"冻结");
	private int code;
	private String name;
	AmountTypeEnum(int code,String name){
		this.setCode(code);
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
