package com.neu.management.model;


import java.sql.Date;
import java.sql.Timestamp;

public class TProductPlan {

  private Long id;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUserid;
  private java.sql.Timestamp updateTime;
  private Long updateUserid;
  private String planSeq;
  private Long orderId;
  private Long productId;
  private Long planCount;
  private java.sql.Date deliveryDate;
  private java.sql.Date planStartDate;
  private java.sql.Date planEndDate;
  private Long planStatus;
  private Long factoryId;

  public TProductPlan() {
  }

  public TProductPlan(Long id, Long flag, Timestamp createTime, Long createUserid, Timestamp updateTime, Long updateUserid, String planSeq, Long orderId, Long productId, Long planCount, Date deliveryDate, Date planStartDate, Date planEndDate, Long planStatus, Long factoryId) {
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

  public String getPlanSeq() {
    return planSeq;
  }

  public void setPlanSeq(String planSeq) {
    this.planSeq = planSeq;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getPlanCount() {
    return planCount;
  }

  public void setPlanCount(Long planCount) {
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

  public Long getPlanStatus() {
    return planStatus;
  }

  public void setPlanStatus(Long planStatus) {
    this.planStatus = planStatus;
  }

  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }
}
