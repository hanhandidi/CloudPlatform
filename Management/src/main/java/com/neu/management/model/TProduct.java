package com.neu.management.model;

import java.sql.Timestamp;

public class TProduct {

    private long id;
    private long flag;
    private Timestamp createTime;
    private long createUserid;
    private Timestamp updateTime;
    private long updateUserid;
    private String productNum;
    private String productName;
    private String productImgUrl;
    private long factoryId;
    private TUser createUser;
    private TUser updateUser;

    public TUser getCreateUser() {
        return createUser;
    }

    public void setCreateUser(TUser createUser) {
        this.createUser = createUser;
    }

    public TUser getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(TUser updateUser) {
        this.updateUser = updateUser;
    }

    public TProduct() {
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

    public String getProductNum() {
        return productNum;
    }

    @Override
    public String toString() {
        return "TProduct{" +
                "id=" + id +
                ", flag=" + flag +
                ", createTime=" + createTime +
                ", createUserid=" + createUserid +
                ", updateTime=" + updateTime +
                ", updateUserid=" + updateUserid +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", productImgUrl='" + productImgUrl + '\'' +
                ", factoryId=" + factoryId +
                '}';
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

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public TProduct(long id, long flag, Timestamp createTime, long createUserid, Timestamp updateTime, long updateUserid, String productNum, String productName, String productImgUrl, long factoryId) {
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
