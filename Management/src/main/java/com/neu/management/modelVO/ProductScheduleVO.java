package com.neu.management.modelVO;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

// 为生产调度指定生产设备
@Data
public class ProductScheduleVO {

    private long id;
    private java.sql.Timestamp updateTime;
    private long updateUserid;
    private long scheduleCount;
    private long equipmentId;
    private java.sql.Date startDate;
    private java.sql.Date endDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public long getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(long updateUserid) {
        this.updateUserid = updateUserid;
    }

    public long getScheduleCount() {
        return scheduleCount;
    }

    public void setScheduleCount(long scheduleCount) {
        this.scheduleCount = scheduleCount;
    }

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
