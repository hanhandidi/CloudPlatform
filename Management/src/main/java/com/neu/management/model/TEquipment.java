package com.neu.management.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;
import java.util.List;

@Mapping(mappingPath = "/json/equipment_mapping.json")
@Document(indexName = "equipment",type = "doc",shards = 1,replicas = 0)
public class TEquipment implements Serializable {
  @Id
  private long id;
  private long flag;
  private java.sql.Timestamp createTime;
  private long createUserid;
  private java.sql.Timestamp updateTime;
  private long updateUserid;
  private String equipmentSeq;
  private String equipmentName;
  private String equipmentImgUrl;
  private long equipmentStatus;
  private long factoryId;
  private List<TEquipmentProduct> tEquipmentProducts;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<TEquipmentProduct> gettEquipmentProducts() {
    return tEquipmentProducts;
  }

  public void settEquipmentProducts(List<TEquipmentProduct> tEquipmentProducts) {
    this.tEquipmentProducts = tEquipmentProducts;
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


  public String getEquipmentSeq() {
    return equipmentSeq;
  }

  public void setEquipmentSeq(String equipmentSeq) {
    this.equipmentSeq = equipmentSeq;
  }


  public String getEquipmentName() {
    return equipmentName;
  }

  public void setEquipmentName(String equipmentName) {
    this.equipmentName = equipmentName;
  }


  public String getEquipmentImgUrl() {
    return equipmentImgUrl;
  }

  public void setEquipmentImgUrl(String equipmentImgUrl) {
    this.equipmentImgUrl = equipmentImgUrl;
  }


  public long getEquipmentStatus() {
    return equipmentStatus;
  }

  public void setEquipmentStatus(long equipmentStatus) {
    this.equipmentStatus = equipmentStatus;
  }


  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }

}
