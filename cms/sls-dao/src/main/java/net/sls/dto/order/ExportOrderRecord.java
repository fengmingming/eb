package net.sls.dto.order;

import java.util.Date;

public class ExportOrderRecord {
    private Long id;

    private String exportNum;

    private Date createTime;

    private Integer number;

    private Integer state;

    private Date lastExportTime;

    private Long operId;

    private Date startdate;

    private Date enddate;

    private Integer isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExportNum() {
        return exportNum;
    }

    public void setExportNum(String exportNum) {
        this.exportNum = exportNum == null ? null : exportNum.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getLastExportTime() {
        return lastExportTime;
    }

    public void setLastExportTime(Date lastExportTime) {
        this.lastExportTime = lastExportTime;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}