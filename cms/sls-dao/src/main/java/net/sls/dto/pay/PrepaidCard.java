package net.sls.dto.pay;

import java.util.Date;

public class PrepaidCard {
    private Integer id;

    private String batch;

    private String cardNum;

    private String password;

    private Integer parValue;

    private Date validityStart;

    private Date validityEnd;

    private Byte checkStatus;

    private Byte cardStatus;
    
    private String remark;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getParValue() {
        return parValue;
    }

    public void setParValue(Integer parValue) {
        this.parValue = parValue;
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

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Byte getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Byte cardStatus) {
        this.cardStatus = cardStatus;
    }

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n===========================");
		sb.append("\r\nCard Id :  "+this.getId());
		sb.append("\r\nCard Num :  "+this.getCardNum());	
		sb.append("\r\nCard pswd :  "+this.getPassword());
		sb.append("\r\nCard chkStatus :  "+this.getCheckStatus());				
		sb.append("\r\n");		
		return sb.toString();
	}
	
}