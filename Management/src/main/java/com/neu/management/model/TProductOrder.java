package com.neu.management.model;


import java.sql.Date;
import java.sql.Timestamp;

public class TProductOrder {

  private long id;
  private long flag;
  private java.sql.Timestamp createTime;
  private long createUserid;
  private java.sql.Timestamp updateTime;
  private long updateUserid;
  private String orderSeq;
  private long orderSource;
  private long productId;
  private long productCount;
  private java.sql.Date endDate;
  private long orderStatus;
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

  public String getOrderSeq() {
    return orderSeq;
  }

  public void setOrderSeq(String orderSeq) {
    this.orderSeq = orderSeq;
  }

  public long getOrderSource() {
    return orderSource;
  }

  public void setOrderSource(long orderSource) {
    this.orderSource = orderSource;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public long getProductCount() {
    return productCount;
  }

  public void setProductCount(long productCount) {
    this.productCount = productCount;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public long getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(long orderStatus) {
    this.orderStatus = orderStatus;
  }

  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }

  public TProductOrder(long id, long flag, Timestamp createTime, long createUserid, Timestamp updateTime, long updateUserid, String orderSeq, long orderSource, long productId, long productCount, Date endDate, long orderStatus, long factoryId, long factoryYield) {
    this.id = id;
    this.flag = flag;
    this.createTime = createTime;
    this.createUserid = createUserid;
    this.updateTime = updateTime;
    this.updateUserid = updateUserid;
    this.orderSeq = orderSeq;
    this.orderSource = orderSource;
    this.productId = productId;
    this.productCount = productCount;
    this.endDate = endDate;
    this.orderStatus = orderStatus;
    this.factoryId = factoryId;
  }

  public TProductOrder() {
  }
}
