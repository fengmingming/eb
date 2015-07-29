package net.sls.dto.pc.product;

import java.io.Serializable;
import java.util.Date;

public class Broadcast implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String imgUrl;

    private String url;

    private Integer sort;

    private Integer cmsUserId;

    private String areaCode;
    
    private Date createTime;
    
    private Date modifyTime;
    
    private Integer isUse;
    
    private Integer isDel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getCmsUserId() {
		return cmsUserId;
	}

	public void setCmsUserId(Integer cmsUserId) {
		this.cmsUserId = cmsUserId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreatetime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifytime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
  
}