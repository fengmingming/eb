package net.sls.dto.pc.act;

import java.io.Serializable;
import java.util.Date;

public class UserCoupon implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String code;

    private Long couponId;

    private Long usrId;

    private Date genDate;

    private Date consumptionDate;

    private Long orderId;

    private String orderNum;
    
    private String name;

    private Integer parValue;

    private Integer minimum;

    private Date validityEnd;
    
    private Long limitCat;
    
    private String limitCatName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLimitCatName() {
		return limitCatName;
	}

	public void setLimitCatName(String limitCatName) {
		this.limitCatName = limitCatName;
	}
}