package net.sls.dto.pc.order;

import java.util.Date;

public class OrderLog {
    private Long id;

    private Long orderId;

    private int operType = 3;
    
    private Long operId;
    
    private String operName;
    
    private Date createTime;
    
    private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getOperType() {
		return operType;
	}

	public void setOperType(int operType) {
		this.operType = operType;
	}

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}
}