package com.neu.management.model;


import java.sql.Timestamp;

public class TRolePermit {

  private Long id;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUserid;
  private java.sql.Timestamp updateTime;
  private Long updateUserid;
  private Long factoryId;
  private Long roleId;
  private Long permitId;

  public TRolePermit() {
  }

  public TRolePermit(Long id, Long flag, Timestamp createTime, Long createUserid, Timestamp updateTime, Long updateUserid, Long factoryId, Long roleId, Long permitId) {
    this.id = id;
    this.flag = flag;
    this.createTime = createTime;
    this.createUserid = createUserid;
    this.updateTime = updateTime;
    this.updateUserid = updateUserid;
    this.factoryId = factoryId;
    this.roleId = roleId;
    this.permitId = permitId;
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

  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getPermitId() {
    return permitId;
  }

  public void setPermitId(Long permitId) {
    this.permitId = permitId;
  }
}
