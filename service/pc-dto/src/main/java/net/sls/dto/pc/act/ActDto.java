package net.sls.dto.pc.act;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动dto
 * @author huzeyu 2015-03-20
 *
 */
public class ActDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long actId;
	private Integer actType;
	private String actTypeName;
	private String actName;
	private BigDecimal actPrice;
	private int number;

	private int actBuyNumber;
	private Date now;
	private Date startTime;
	private Date endTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public String getActTypeName() {
		return actTypeName;
	}
	public void setActTypeName(String actTypeName) {
		this.actTypeName = actTypeName;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public BigDecimal getActPrice() {
		return actPrice;
	}
	public void setActPrice(BigDecimal actPrice) {
		this.actPrice = actPrice;
	}
	public int getActBuyNumber() {
		return actBuyNumber;
	}
	public void setActBuyNumber(int actBuyNumber) {
		this.actBuyNumber = actBuyNumber;
	}
	public Date getNow() {
		return now;
	}
	public void setNow(Date now) {
		this.now = now;
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
}
