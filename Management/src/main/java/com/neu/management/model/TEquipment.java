package com.neu.management.model;


public class TEquipment {

  private Long id;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUserid;
  private java.sql.Timestamp updateTime;
  private Long updateUserid;
  private String equipmentSeq;
  private String equipmentName;
  private String equipmentImgUrl;
  private Long equipmentStatus;
  private Long factoryId;


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


  public String getEquipmentSeq() {
    return equipmentSeq;
  }

  public void setEquipmentSeq(String equipmentSeq) {
    this.equipmentSeq = equipmentSeq;
  }


  public String getEquipmentName() {
    return equipmentName;
  }

  public void setEquipmentName(String equipmentName) {
    this.equipmentName = equipmentName;
  }


  public String getEquipmentImgUrl() {
    return equipmentImgUrl;
  }

  public void setEquipmentImgUrl(String equipmentImgUrl) {
    this.equipmentImgUrl = equipmentImgUrl;
  }


  public Long getEquipmentStatus() {
    return equipmentStatus;
  }

  public void setEquipmentStatus(Long equipmentStatus) {
    this.equipmentStatus = equipmentStatus;
  }


  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }

}
