package net.sls.dto.pc.pay;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author sls006
 * 
 * 预付费卡实体类
 *
 */
public class PrepaidCard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer id;

    private String batch;

    private String cardNum;

    private String password;

    private Integer parValue;

    private Date validityStart;

    private Date validityEnd;

    private Byte checkStatus;

    private Byte cardStatus;
    
    private Long userId;
    
    private Date reChangeDate;

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getReChangeDate() {
		return reChangeDate;
	}

	public void setReChangeDate(Date reChangeDate) {
		this.reChangeDate = reChangeDate;
	}

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
}