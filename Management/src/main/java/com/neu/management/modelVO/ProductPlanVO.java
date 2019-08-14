package com.neu.management.modelVO;

import lombok.Data;

import java.sql.Date;

// 填写计划起始日期可生成对应的生产计划
@Data
public class ProductPlanVO {

    private long planCount;
    private java.sql.Date planStartDate;
    private java.sql.Date planEndDate;
    private long userId;

    public long getPlanCount() {
        return planCount;
    }

    public void setPlanCount(long planCount) {
        this.planCount = planCount;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
