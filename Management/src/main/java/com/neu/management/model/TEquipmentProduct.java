package com.neu.management.model;


public class TEquipmentProduct {

  private Long id;
  private Long equipmentId;
  private Long productId;
  private Long yield;
  private Long unit;
  private Long factoryId;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(Long equipmentId) {
    this.equipmentId = equipmentId;
  }


  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }


  public Long getYield() {
    return yield;
  }

  public void setYield(Long yield) {
    this.yield = yield;
  }


  public Long getUnit() {
    return unit;
  }

  public void setUnit(Long unit) {
    this.unit = unit;
  }


  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }

}
