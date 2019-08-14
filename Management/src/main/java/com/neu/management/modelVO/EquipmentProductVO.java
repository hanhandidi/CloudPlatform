package com.neu.management.modelVO;

// 前端 产能信息
public class EquipmentProductVO {

    private long productId;
    private long yield;
    private long unit;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getYield() {
        return yield;
    }

    public void setYield(long yield) {
        this.yield = yield;
    }

    public long getUnit() {
        return unit;
    }

    public void setUnit(long unit) {
        this.unit = unit;
    }
}
