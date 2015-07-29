package net.sls.dto.pc.product;

import java.io.Serializable;

public class GoodsCategory implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long oneId;

    private Long twoId;

    private Long threeId;

    private Long goodsId;
    
    private String firstName;
    
    private String secondName;
    
    private String thirdName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOneId() {
        return oneId;
    }

    public void setOneId(Long oneId) {
        this.oneId = oneId;
    }

    public Long getTwoId() {
        return twoId;
    }

    public void setTwoId(Long twoId) {
        this.twoId = twoId;
    }

    public Long getThreeId() {
        return threeId;
    }

    public void setThreeId(Long threeId) {
        this.threeId = threeId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}
}