package com.neu.management.model;


public class TPermit {

  private long id;
  private long flag;
  private long status;
  private long parentId;
  private String permitName;
  private String permitDesc;
  private String permitPath;


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


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }


  public String getPermitName() {
    return permitName;
  }

  public void setPermitName(String permitName) {
    this.permitName = permitName;
  }


  public String getPermitDesc() {
    return permitDesc;
  }

  public void setPermitDesc(String permitDesc) {
    this.permitDesc = permitDesc;
  }


  public String getPermitPath() {
    return permitPath;
  }

  public void setPermitPath(String permitPath) {
    this.permitPath = permitPath;
  }

}
