package net.sls.dto.pc.act;

import java.io.Serializable;
import java.util.Date;

public class Dynpage implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String title;

    private Date createtime;

    private Long cmsUserId;

    private Integer isDel;
    
    private Integer type;

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getCmsUserId() {
        return cmsUserId;
    }

    public void setCmsUserId(Long cmsUserId) {
        this.cmsUserId = cmsUserId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}