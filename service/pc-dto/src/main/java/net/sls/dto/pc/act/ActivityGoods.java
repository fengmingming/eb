package net.sls.dto.pc.act;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityGoods implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long actId;

    private Integer actType;

    private Long goodsId;

    private BigDecimal actPrice;

    private Date startTime;

    private Date endTime;

    private Integer number;
    
    private Long userId;
    
    private Integer buyNumber = 0;
    
    private Integer canBuyNumber = Integer.MAX_VALUE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public Integer getActType() {
        return actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getActPrice() {
        return actPrice;
    }

    public void setActPrice(BigDecimal actPrice) {
        this.actPrice = actPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 活动商品限购的数量
     * @return
     */
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 商品购买数量（如果有userId，为该用户购买的数量，反之为购买的总数量）
	 * @return
	 */
	public Integer getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	/**
	 * 商品可购买数（如果有userId，是当前用还能该买的数量，反之是限购的数量）
	 * @return
	 */
	public Integer getCanBuyNumber() {
		return canBuyNumber;
	}

	public void setCanBuyNumber(Integer canBuyNumber) {
		this.canBuyNumber = canBuyNumber;
	}
}