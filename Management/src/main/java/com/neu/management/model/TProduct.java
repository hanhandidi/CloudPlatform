package com.neu.management.model;

import java.sql.Timestamp;

public class TProduct {

    private Long id;
    private Long flag;
    private Timestamp createTime;
    private Long createUserid;
    private Timestamp updateTime;
    private Long updateUserid;
    private String productNum;
    private String productName;
    private String productImgUrl;
    private Long factoryId;

    public TProduct() {
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

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public TProduct(Long id, Long flag, Timestamp createTime, Long createUserid, Timestamp updateTime, Long updateUserid, String productNum, String productName, String productImgUrl, Long factoryId) {
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
