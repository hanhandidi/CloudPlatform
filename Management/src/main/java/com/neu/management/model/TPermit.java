package com.neu.management.model;


public class TPermit {

  private Long id;
  private Long flag;
  private Long status;
  private Long parentId;
  private String permitName;
  private String permitDesc;
  private String permitPath;


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


  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }


  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
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
