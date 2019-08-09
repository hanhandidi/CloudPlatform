package com.neu.management.model;


public class TDailyWork {

  private Long id;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUserid;
  private java.sql.Timestamp updateTime;
  private Long updateUserid;
  private Long scheduleId;
  private Long equipmentId;
  private String equipmentSeq;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private Long workingCount;
  private Long qualifiedCount;
  private Long unqualifiedCout;
  private Long completeFlag;
  private Long factoryId;
  private String bak;


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


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public Long getCreateUserid() {
    return createUserid;
  }

  public void setCreateUserid(Long createUserid) {
    this.createUserid = createUserid;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public Long getUpdateUserid() {
    return updateUserid;
  }

  public void setUpdateUserid(Long updateUserid) {
    this.updateUserid = updateUserid;
  }


  public Long getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Long scheduleId) {
    this.scheduleId = scheduleId;
  }


  public Long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(Long equipmentId) {
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


  public Long getWorkingCount() {
    return workingCount;
  }

  public void setWorkingCount(Long workingCount) {
    this.workingCount = workingCount;
  }


  public Long getQualifiedCount() {
    return qualifiedCount;
  }

  public void setQualifiedCount(Long qualifiedCount) {
    this.qualifiedCount = qualifiedCount;
  }


  public Long getUnqualifiedCout() {
    return unqualifiedCout;
  }

  public void setUnqualifiedCout(Long unqualifiedCout) {
    this.unqualifiedCout = unqualifiedCout;
  }


  public Long getCompleteFlag() {
    return completeFlag;
  }

  public void setCompleteFlag(Long completeFlag) {
    this.completeFlag = completeFlag;
  }


  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }


  public String getBak() {
    return bak;
  }

  public void setBak(String bak) {
    this.bak = bak;
  }

}
