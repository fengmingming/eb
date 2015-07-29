package net.sls.dto.user;

import java.math.BigDecimal;
import java.util.Date;

public class AmountOrder {
    private Long id;

    private String voucherOrderNum;

    private Long userId;

    private BigDecimal money;

    private Integer status;

    private Date createTime;

    private String remark;

    private String thirdOrderNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucherOrderNum() {
        return voucherOrderNum;
    }

    public void setVoucherOrderNum(String voucherOrderNum) {
        this.voucherOrderNum = voucherOrderNum == null ? null : voucherOrderNum.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getThirdOrderNum() {
        return thirdOrderNum;
    }

    public void setThirdOrderNum(String thirdOrderNum) {
        this.thirdOrderNum = thirdOrderNum == null ? null : thirdOrderNum.trim();
    }
}