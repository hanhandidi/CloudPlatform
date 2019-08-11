package com.neu.management.modelVO;

import lombok.Data;

// 查询：根据产品名称、设备名称查询当前工厂所有相关设备内容列表
@Data
public class EquipmentSelectVO {

    private String productName;
    private String equipmentName;
    private long factoryId;

}
