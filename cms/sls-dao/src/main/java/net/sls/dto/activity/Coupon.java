package net.sls.dto.activity;

import java.util.Date;

public class Coupon {
    private Long id;

    private String name;

    private Integer parValue;

    private Integer minimum;

    private Integer total;

    private Integer num;

    private Integer limitType;

    private Date validityStart;

    private Date validityEnd;

    private Long limitCat;
    
    private String limitCatName;
    
    private Date genDate;

    private Integer isDel;

    private Date startdate;

    private Date enddate;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParValue() {
        return parValue;
    }

    public void setParValue(Integer parValue) {
        this.parValue = parValue;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getLimitType() {
        return limitType;
    }

    public void setLimitType(Integer limitType) {
        this.limitType = limitType;
    }

    public Date getValidityStart() {
        return validityStart;
    }

    public void setValidityStart(Date validityStart) {
        this.validityStart = validityStart;
    }

    public Date getValidityEnd() {
        return validityEnd;
    }

    public void setValidityEnd(Date validityEnd) {
        this.validityEnd = validityEnd;
    }

    public Long getLimitCat() {
        return limitCat;
    }

    public void setLimitCat(Long limitCat) {
        this.limitCat = limitCat;
    }

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getLimitCatName() {
		return limitCatName;
	}

	public void setLimitCatName(String limitCatName) {
		this.limitCatName = limitCatName;
	}
}