package net.sls.dto.pc.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AmountLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;

    private BigDecimal money;

    private Long operator;

    private Integer type;

    private Date createTime;
    
    private String remark;
    
    private BigDecimal curAmount;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getCurAmount() {
		return curAmount;
	}

	public void setCurAmount(BigDecimal curAmount) {
		this.curAmount = curAmount;
	}
}
