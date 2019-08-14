package com.neu.management.model;


public class TDailyWork {

  private long id;
  private long flag;
  private java.sql.Timestamp createTime;
  private long createUserid;
  private java.sql.Timestamp updateTime;
  private long updateUserid;
  private long orderTrackId;
  private long scheduleId;
  private long equipmentId;
  private String equipmentSeq;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private long workingCount;
  private long qualifiedCount;
  private long unqualifiedCout;
  private long completeFlag;
  private long factoryId;
  private String bak;
  private TProductSchedule tProductSchedule;

  public long getOrderTrackId() {
    return orderTrackId;
  }

  public void setOrderTrackId(long orderTrackId) {
    this.orderTrackId = orderTrackId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public TProductSchedule gettProductSchedule() {
    return tProductSchedule;
  }

  public void settProductSchedule(TProductSchedule tProductSchedule) {
    this.tProductSchedule = tProductSchedule;
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


  public long getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(long scheduleId) {
    this.scheduleId = scheduleId;
  }


  public long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(long equipmentId) {
    this.equipmentId = equipmentId;
  }


  public String getEquipmentSeq() {
    return equipmentSeq;
  }

  public void setEquipmentSeq(String equipmentSeq) {
    this.equipmentSeq = equipmentSeq;
  }


  public java.sql.Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Timestamp startTime) {
    this.startTime = startTime;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
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


  public long getUnqualifiedCout() {
    return unqualifiedCout;
  }

  public void setUnqualifiedCout(long unqualifiedCout) {
    this.unqualifiedCout = unqualifiedCout;
  }


  public long getCompleteFlag() {
    return completeFlag;
  }

  public void setCompleteFlag(long completeFlag) {
    this.completeFlag = completeFlag;
  }


  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }


  public String getBak() {
    return bak;
  }

  public void setBak(String bak) {
    this.bak = bak;
  }

}
