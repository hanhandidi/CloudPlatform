package com.neu.authority.entity;


import java.sql.Timestamp;

public class TUserRole {

  private long id;
  private long flag;
  private Timestamp createTime;
  private long createUserid;
  private Timestamp updateTime;
  private long updateUserid;
  private String roleDesc;
  private String roleName;
  private long roleStatus;
  private long factoryId;

  public TUserRole() {
  }

  public TUserRole(long id, long flag, Timestamp createTime, long createUserid, Timestamp updateTime, long updateUserid, String roleDesc, String roleName, long roleStatus, long factoryId) {
    this.id = id;
    this.flag = flag;
    this.createTime = createTime;
    this.createUserid = createUserid;
    this.updateTime = updateTime;
    this.updateUserid = updateUserid;
    this.roleDesc = roleDesc;
    this.roleName = roleName;
    this.roleStatus = roleStatus;
    this.factoryId = factoryId;
  }

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


  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getCreateUserid() {
    return createUserid;
  }

  public void setCreateUserid(long createUserid) {
    this.createUserid = createUserid;
  }


  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public long getUpdateUserid() {
    return updateUserid;
  }

  public void setUpdateUserid(long updateUserid) {
    this.updateUserid = updateUserid;
  }


  public String getRoleDesc() {
    return roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public long getRoleStatus() {
    return roleStatus;
  }

  public void setRoleStatus(long roleStatus) {
    this.roleStatus = roleStatus;
  }


  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }

}
