package com.neu.management.modelVO;

import java.sql.Timestamp;

// 成功报工信息需要填写的数据
public class DailyWorkVO {

    private java.sql.Timestamp startTime;
    private java.sql.Timestamp endTime;
    private long workingCount;
    private long createUserid;
    private long qualifiedCount;
    private long unqualifiedCout;
    private String bak;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public long getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(long createUserid) {
        this.createUserid = createUserid;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public long getWorkingCount() {
        return workingCount;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
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

    public long getUnqualifiedCout() {
        return unqualifiedCout;
    }

    public void setUnqualifiedCout(long unqualifiedCout) {
        this.unqualifiedCout = unqualifiedCout;
    }
}
