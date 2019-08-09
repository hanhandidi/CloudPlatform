package com.neu.management.model;


public class TFactory {

  private Long id;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUserid;
  private java.sql.Timestamp updateTime;
  private Long updateUserid;
  private String bak;
  private String factoryName;
  private String factoryImgUrl;
  private String factoryAddr;
  private String factoryUrl;
  private Long factoryWorker;
  private Long factoryStatus;


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


  public Long getFactoryWorker() {
    return factoryWorker;
  }

  public void setFactoryWorker(Long factoryWorker) {
    this.factoryWorker = factoryWorker;
  }


  public Long getFactoryStatus() {
    return factoryStatus;
  }

  public void setFactoryStatus(Long factoryStatus) {
    this.factoryStatus = factoryStatus;
  }

}
