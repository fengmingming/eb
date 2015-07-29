package net.sls.dto.pc.user;

import java.io.Serializable;
import java.math.BigDecimal;

public class AmountBeanDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String voucherOrderNum;
	
	private String thirdOrderNum;
	
	private BigDecimal money;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getVoucherOrderNum() {
		return voucherOrderNum;
	}

	public void setVoucherOrderNum(String voucherOrderNum) {
		this.voucherOrderNum = voucherOrderNum;
	}

	public String getThirdOrderNum() {
		return thirdOrderNum;
	}

	public void setThirdOrderNum(String thirdOrderNum) {
		this.thirdOrderNum = thirdOrderNum;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	
}
