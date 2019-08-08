package com.sample;


public class TProductSchedule {

  private long id;
  private long flag;
  private java.sql.Timestamp createTime;
  private long createUserid;
  private java.sql.Timestamp updateTime;
  private long updateUserid;
  private String scheduleSeq;
  private long scheduleCount;
  private long scheduleStatus;
  private long planId;
  private long productId;
  private long equipmentId;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private long factoryId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getFlag() {
    return flag;
  }

  public void setFlag(long flag) {
    this.flag = flag;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getCreateUserid() {
    return createUserid;
  }

  public void setCreateUserid(long createUserid) {
    this.createUserid = createUserid;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public long getUpdateUserid() {
    return updateUserid;
  }

  public void setUpdateUserid(long updateUserid) {
    this.updateUserid = updateUserid;
  }


  public String getScheduleSeq() {
    return scheduleSeq;
  }

  public void setScheduleSeq(String scheduleSeq) {
    this.scheduleSeq = scheduleSeq;
  }


  public long getScheduleCount() {
    return scheduleCount;
  }

  public void setScheduleCount(long scheduleCount) {
    this.scheduleCount = scheduleCount;
  }


  public long getScheduleStatus() {
    return scheduleStatus;
  }

  public void setScheduleStatus(long scheduleStatus) {
    this.scheduleStatus = scheduleStatus;
  }


  public long getPlanId() {
    return planId;
  }

  public void setPlanId(long planId) {
    this.planId = planId;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(long equipmentId) {
    this.equipmentId = equipmentId;
  }


  public java.sql.Date getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Date startDate) {
    this.startDate = startDate;
  }


  public java.sql.Date getEndDate() {
    return endDate;
  }

  public void setEndDate(java.sql.Date endDate) {
    this.endDate = endDate;
  }


  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }

}
