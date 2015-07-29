package util.model;

/**
 * 用户类型
 * @author huzeyu 2015-02-09
 *
 */
public enum MemberTypeEnum {
	MemberType_1(1,"普通用户"),
	MemberType_2(2,"服务亭");
	
	private int code;
	private String name;
	
	MemberTypeEnum(int code,String name){
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
