package com.neu.management.model;


import java.sql.Date;
import java.sql.Timestamp;

public class TProductOrder {

  private Long id;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUserid;
  private java.sql.Timestamp updateTime;
  private Long updateUserid;
  private String orderSeq;
  private Long orderSource;
  private Long productId;
  private Long productCount;
  private java.sql.Date endDate;
  private Long orderStatus;
  private Long factoryId;
  private Long factoryYield;

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

  public String getOrderSeq() {
    return orderSeq;
  }

  public void setOrderSeq(String orderSeq) {
    this.orderSeq = orderSeq;
  }

  public Long getOrderSource() {
    return orderSource;
  }

  public void setOrderSource(Long orderSource) {
    this.orderSource = orderSource;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getProductCount() {
    return productCount;
  }

  public void setProductCount(Long productCount) {
    this.productCount = productCount;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Long getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Long orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }

  public Long getFactoryYield() {
    return factoryYield;
  }

  public void setFactoryYield(Long factoryYield) {
    this.factoryYield = factoryYield;
  }

  public TProductOrder(Long id, Long flag, Timestamp createTime, Long createUserid, Timestamp updateTime, Long updateUserid, String orderSeq, Long orderSource, Long productId, Long productCount, Date endDate, Long orderStatus, Long factoryId, Long factoryYield) {
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
    this.factoryYield = factoryYield;
  }

  public TProductOrder() {
  }
}
