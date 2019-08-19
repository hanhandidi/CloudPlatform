package com.neu.authority.entity;


import java.sql.Timestamp;
import java.util.List;

public class TRolePermit {

    private long id;
    private long flag;
    private Timestamp createTime;
    private long createUserid;
    private Timestamp updateTime;
    private long updateUserid;
    private long factoryId;
    private long roleId;
    private long permitId;
    private TPermit permit;
    // 该roleId下的permitIds
    private List<Long> permitIds;

    public TRolePermit() {
    }

    // 修改用（选中）
    public TRolePermit(long updateUserid, long factoryId, long roleId, long permitId) {
        this.updateUserid = updateUserid;
        this.factoryId = factoryId;
        this.roleId = roleId;
        this.permitId = permitId;
    }

    // 新增用
    public TRolePermit(long flag, long createUserid, long updateUserid, long factoryId, long roleId, long permitId) {
        this.flag = flag;
        this.createUserid = createUserid;
        this.updateUserid = updateUserid;
        this.factoryId = factoryId;
        this.roleId = roleId;
        this.permitId = permitId;
    }

    public TRolePermit(long id, long flag, Timestamp createTime, long createUserid, Timestamp updateTime, long updateUserid, long factoryId, long roleId, long permitId) {
        this.id = id;
        this.flag = flag;
        this.createTime = createTime;
        this.createUserid = createUserid;
        this.updateTime = updateTime;
        this.updateUserid = updateUserid;
        this.factoryId = factoryId;
        this.roleId = roleId;
        this.permitId = permitId;
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

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getPermitId() {
        return permitId;
    }

    public void setPermitId(long permitId) {
        this.permitId = permitId;
    }

    public TPermit getPermit() {
        return permit;
    }

    public void setPermit(TPermit permit) {
        this.permit = permit;
    }

    public List<Long> getPermitIds() {
        return permitIds;
    }

    public void setPermitIds(List<Long> permitIds) {
        this.permitIds = permitIds;
    }
}
