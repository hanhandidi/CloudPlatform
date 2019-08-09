package com.neu.management.model;


import java.sql.Date;
import java.sql.Timestamp;

public class TProductPlan {

  private long id;
  private long flag;
  private java.sql.Timestamp createTime;
  private long createUserid;
  private java.sql.Timestamp updateTime;
  private long updateUserid;
  private String planSeq;
  private long orderId;
  private long productId;
  private long planCount;
  private java.sql.Date deliveryDate;
  private java.sql.Date planStartDate;
  private java.sql.Date planEndDate;
  private long planStatus;
  private long factoryId;

  public TProductPlan() {
  }

  public TProductPlan(long id, long flag, Timestamp createTime, long createUserid, Timestamp updateTime, long updateUserid, String planSeq, long orderId, long productId, long planCount, Date deliveryDate, Date planStartDate, Date planEndDate, long planStatus, long factoryId) {
    this.id = id;
    this.flag = flag;
    this.createTime = createTime;
    this.createUserid = createUserid;
    this.updateTime = updateTime;
    this.updateUserid = updateUserid;
    this.planSeq = planSeq;
    this.orderId = orderId;
    this.productId = productId;
    this.planCount = planCount;
    this.deliveryDate = deliveryDate;
    this.planStartDate = planStartDate;
    this.planEndDate = planEndDate;
    this.planStatus = planStatus;
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

  public String getPlanSeq() {
    return planSeq;
  }

  public void setPlanSeq(String planSeq) {
    this.planSeq = planSeq;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public long getPlanCount() {
    return planCount;
  }

  public void setPlanCount(long planCount) {
    this.planCount = planCount;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public Date getPlanStartDate() {
    return planStartDate;
  }

  public void setPlanStartDate(Date planStartDate) {
    this.planStartDate = planStartDate;
  }

  public Date getPlanEndDate() {
    return planEndDate;
  }

  public void setPlanEndDate(Date planEndDate) {
    this.planEndDate = planEndDate;
  }

  public long getPlanStatus() {
    return planStatus;
  }

  public void setPlanStatus(long planStatus) {
    this.planStatus = planStatus;
  }

  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }
}
