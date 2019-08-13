package com.neu.management.modelVO;

import java.sql.Timestamp;
import java.util.List;

// 带有多个产能信息的设备添加视图层
public class EquipmentAddVO {

    private long flag;
    private java.sql.Timestamp createTime;
    private long createUserid;
    private String equipmentSeq;
    private String equipmentName;
    private String equipmentImgUrl;
    private long equipmentStatus;
    private long factoryId;
    private List<EquipmentProductVO> equipmentProductVOS;

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

    public List<EquipmentProductVO> getEquipmentProductVOS() {
        return equipmentProductVOS;
    }

    public void setEquipmentProductVOS(List<EquipmentProductVO> equipmentProductVOS) {
        this.equipmentProductVOS = equipmentProductVOS;
    }
}
