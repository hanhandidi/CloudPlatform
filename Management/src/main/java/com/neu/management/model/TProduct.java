package com.neu.management.model;

import java.sql.Timestamp;

public class TProduct {

    private Integer id;
    private Integer flag;
    private java.sql.Timestamp createTime;
    private Integer createUserid;
    private java.sql.Timestamp updateTime;
    private Integer updateUserid;
    private String productNum;
    private String productName;
    private String productImgUrl;
    private Integer factoryId;

    public TProduct() {
    }

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public TProduct(Integer id, Integer flag, Timestamp createTime, Integer createUserid, Timestamp updateTime, Integer updateUserid, String productNum, String productName, String productImgUrl, Integer factoryId) {
        this.id = id;
        this.flag = flag;
        this.createTime = createTime;
        this.createUserid = createUserid;
        this.updateTime = updateTime;
        this.updateUserid = updateUserid;
        this.productNum = productNum;
        this.productName = productName;
        this.productImgUrl = productImgUrl;
        this.factoryId = factoryId;
    }
}
