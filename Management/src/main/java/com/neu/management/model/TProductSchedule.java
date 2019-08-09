package com.neu.management.model;


import java.sql.Date;
import java.sql.Timestamp;

public class TProductSchedule {

  private Long id;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUserid;
  private java.sql.Timestamp updateTime;
  private Long updateUserid;
  private String scheduleSeq;
  private Long scheduleCount;
  private Long scheduleStatus;
  private Long planId;
  private Long productId;
  private Long equipmentId;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private Long factoryId;

  public TProductSchedule() {
  }

  public TProductSchedule(Long id, Long flag, Timestamp createTime, Long createUserid, Timestamp updateTime, Long updateUserid, String scheduleSeq, Long scheduleCount, Long scheduleStatus, Long planId, Long productId, Long equipmentId, Date startDate, Date endDate, Long factoryId) {
    this.id = id;
    this.flag = flag;
    this.createTime = createTime;
    this.createUserid = createUserid;
    this.updateTime = updateTime;
    this.updateUserid = updateUserid;
    this.scheduleSeq = scheduleSeq;
    this.scheduleCount = scheduleCount;
    this.scheduleStatus = scheduleStatus;
    this.planId = planId;
    this.productId = productId;
    this.equipmentId = equipmentId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.factoryId = factoryId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getFlag() {
    return flag;
  }

  public void setFlag(Long flag) {
    this.flag = flag;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Long getCreateUserid() {
    return createUserid;
  }

  public void setCreateUserid(Long createUserid) {
    this.createUserid = createUserid;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public Long getUpdateUserid() {
    return updateUserid;
  }

  public void setUpdateUserid(Long updateUserid) {
    this.updateUserid = updateUserid;
  }

  public String getScheduleSeq() {
    return scheduleSeq;
  }

  public void setScheduleSeq(String scheduleSeq) {
    this.scheduleSeq = scheduleSeq;
  }

  public Long getScheduleCount() {
    return scheduleCount;
  }

  public void setScheduleCount(Long scheduleCount) {
    this.scheduleCount = scheduleCount;
  }

  public Long getScheduleStatus() {
    return scheduleStatus;
  }

  public void setScheduleStatus(Long scheduleStatus) {
    this.scheduleStatus = scheduleStatus;
  }

  public Long getPlanId() {
    return planId;
  }

  public void setPlanId(Long planId) {
    this.planId = planId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(Long equipmentId) {
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

  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }
}
