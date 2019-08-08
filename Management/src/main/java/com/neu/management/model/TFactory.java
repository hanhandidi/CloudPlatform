package com.neu.management.model;


public class TFactory {

  private long id;
  private long flag;
  private java.sql.Timestamp createTime;
  private long createUserid;
  private java.sql.Timestamp updateTime;
  private long updateUserid;
  private String bak;
  private String factoryName;
  private String factoryImgUrl;
  private String factoryAddr;
  private String factoryUrl;
  private long factoryWorker;
  private long factoryStatus;


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


  public String getBak() {
    return bak;
  }

  public void setBak(String bak) {
    this.bak = bak;
  }


  public String getFactoryName() {
    return factoryName;
  }

  public void setFactoryName(String factoryName) {
    this.factoryName = factoryName;
  }


  public String getFactoryImgUrl() {
    return factoryImgUrl;
  }

  public void setFactoryImgUrl(String factoryImgUrl) {
    this.factoryImgUrl = factoryImgUrl;
  }


  public String getFactoryAddr() {
    return factoryAddr;
  }

  public void setFactoryAddr(String factoryAddr) {
    this.factoryAddr = factoryAddr;
  }


  public String getFactoryUrl() {
    return factoryUrl;
  }

  public void setFactoryUrl(String factoryUrl) {
    this.factoryUrl = factoryUrl;
  }


  public long getFactoryWorker() {
    return factoryWorker;
  }

  public void setFactoryWorker(long factoryWorker) {
    this.factoryWorker = factoryWorker;
  }


  public long getFactoryStatus() {
    return factoryStatus;
  }

  public void setFactoryStatus(long factoryStatus) {
    this.factoryStatus = factoryStatus;
  }

}
