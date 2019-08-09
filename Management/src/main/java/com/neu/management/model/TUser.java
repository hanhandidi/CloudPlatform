package com.neu.management.model;


public class TUser {

  private Long id;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUserid;
  private java.sql.Timestamp updateTime;
  private Long updateUserid;
  private Long userStatus;
  private String userName;
  private String userRealName;
  private String userPasswd;
  private String userJobNum;
  private String userPhoneNum;
  private String userEmail;
  private Long roleId;
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


  public Long getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Long userStatus) {
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


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }


  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }

}
