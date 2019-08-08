package com.sample;


public class TUser {

  private long id;
  private long flag;
  private java.sql.Timestamp createTime;
  private long createUserid;
  private java.sql.Timestamp updateTime;
  private long updateUserid;
  private long userStatus;
  private String userName;
  private String userRealName;
  private String userPasswd;
  private String userJobNum;
  private String userPhoneNum;
  private String userEmail;
  private long roleId;
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


  public long getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(long userStatus) {
    this.userStatus = userStatus;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserRealName() {
    return userRealName;
  }

  public void setUserRealName(String userRealName) {
    this.userRealName = userRealName;
  }


  public String getUserPasswd() {
    return userPasswd;
  }

  public void setUserPasswd(String userPasswd) {
    this.userPasswd = userPasswd;
  }


  public String getUserJobNum() {
    return userJobNum;
  }

  public void setUserJobNum(String userJobNum) {
    this.userJobNum = userJobNum;
  }


  public String getUserPhoneNum() {
    return userPhoneNum;
  }

  public void setUserPhoneNum(String userPhoneNum) {
    this.userPhoneNum = userPhoneNum;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }

}
