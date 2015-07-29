package net.sls.dto.pc.user;

import java.io.Serializable;
import java.util.Date;

public class MyFavorite implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	    private Long userId;

	    private Long goodsId;

	    private Integer isDel;
	    
	    private Date createTime;

	    public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

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

	    public Long getGoodsId() {
	        return goodsId;
	    }

	    public void setGoodsId(Long goodsId) {
	        this.goodsId = goodsId;
	    }

	    public Integer getIsDel() {
	        return isDel;
	    }

	    public void setIsDel(Integer isDel) {
	        this.isDel = isDel;
	    }
}
