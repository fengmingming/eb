package util.model;

/**
 * 订单的购买类型：自购、代购
 * @author huzeyu 2015-02-09
 *
 */
public enum ShopTypeEnum {
	ShopType_1(1, "自购", "ZG"),
	ShopType_2(2, "代购", "DG");
	
	private int code;
	private String name;
	private String prefix;
	
	ShopTypeEnum(int code, String name, String prefix){
		this.code = code;
		this.name = name;
		this.prefix = prefix;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public String getPrefix() {
		return prefix;
	}
}
