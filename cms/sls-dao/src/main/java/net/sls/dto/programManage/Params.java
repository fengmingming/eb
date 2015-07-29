package net.sls.dto.programManage;

public class Params {
	private Long id;
	private Long methodId;
	public Long getMethodId() {
		return methodId;
	}
	public void setMethodId(Long methodId) {
		this.methodId = methodId;
	}
	private String reqparams;
	private String paramsName;
	public String getParamsName() {
		return paramsName;
	}
	public void setParamsName(String paramsName) {
		this.paramsName = paramsName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReqparams() {
		return reqparams;
	}
	public void setReqparams(String reqparams) {
		this.reqparams = reqparams;
	}
}
