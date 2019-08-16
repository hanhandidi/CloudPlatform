package com.neu.management.modelVO;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

// 填写计划起始日期可生成对应的生产计划
@Data
public class ProductPlanVO {

    private long planCount;
    private java.sql.Timestamp planStartDate;
    private java.sql.Timestamp planEndDate;
    private long userId;

    public long getPlanCount() {
        return planCount;
    }

    public void setPlanCount(long planCount) {
        this.planCount = planCount;
    }

    public Timestamp getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Timestamp planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Timestamp getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Timestamp planEndDate) {
        this.planEndDate = planEndDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
