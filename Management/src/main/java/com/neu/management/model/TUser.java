package com.neu.management.model;


public class TUser {

  private Integer id;
  private Integer flag;
  private java.sql.Timestamp createTime;
  private Integer createUserid;
  private java.sql.Timestamp updateTime;
  private Integer updateUserid;
  private Integer userStatus;
  private String userName;
  private String userRealName;
  private String userPasswd;
  private String userJobNum;
  private String userPhoneNum;
  private String userEmail;
  private Integer roleId;
  private Integer factoryId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getFlag() {
    return flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public Integer getCreateUserid() {
    return createUserid;
  }

  public void setCreateUserid(Integer createUserid) {
    this.createUserid = createUserid;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public Integer getUpdateUserid() {
    return updateUserid;
  }

  public void setUpdateUserid(Integer updateUserid) {
    this.updateUserid = updateUserid;
  }


  public Integer getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Integer userStatus) {
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


  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }


  public Integer getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Integer factoryId) {
    this.factoryId = factoryId;
  }

}
