package net.sls.dto.activity;

public class DetailTipRel {
    private Long id;

    private Long detailTipId;

    private Long activityId;

    private Integer isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailTipId() {
        return detailTipId;
    }

    public void setDetailTipId(Long detailTipId) {
        this.detailTipId = detailTipId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}