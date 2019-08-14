package com.neu.management.modelVO;

import java.sql.Timestamp;

// 编辑订单跟踪情况
public class OrderTrackVO {

    private long id;
    private long updateUserid;
    private java.sql.Timestamp updateTime;
    private long workingCount;
    private long qualifiedCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUpdateUserid() {
        return updateUserid;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateUserid(long updateUserid) {
        this.updateUserid = updateUserid;
    }

    public long getWorkingCount() {
        return workingCount;
    }

    public void setWorkingCount(long workingCount) {
        this.workingCount = workingCount;
    }

    public long getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(long qualifiedCount) {
        this.qualifiedCount = qualifiedCount;
    }
}
