package net.sls.dto.product;

public class PavilionArea {
    private Long id;

    private Integer pavilionId;

    private String paName;

    private Integer isUse;

    private Integer isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPavilionId() {
        return pavilionId;
    }

    public void setPavilionId(Integer pavilionId) {
        this.pavilionId = pavilionId;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName == null ? null : paName.trim();
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