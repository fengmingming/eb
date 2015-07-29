package net.sls.dto.programManage;

public class Methods {
	
	private Long id;
	private Long Iid;
	private String methodName;
	private String methodEn;
	private String model;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMethodEn() {
		return methodEn;
	}
	public void setMethodEn(String methodEn) {
		this.methodEn = methodEn;
	}
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIid() {
		return Iid;
	}
	public void setIid(Long iid) {
		Iid = iid;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
