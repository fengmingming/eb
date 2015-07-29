package net.sls.dto.user;

import java.math.BigDecimal;
import java.util.Date;

public class AmountLog {
    private Long id;

    private Long userId;

    private BigDecimal money;

    private BigDecimal curAmount;

    private Long operator;

    private Integer type;

    private Date createTime;

    private String remark;

    private Integer origin;

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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getCurAmount() {
        return curAmount;
    }

    public void setCurAmount(BigDecimal curAmount) {
        this.curAmount = curAmount;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }
}