package com.neu.management.dao;

import com.neu.management.model.TEquipmentProduct;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface EquipmentProductDao {

    @Insert("insert into t_equipment_product(equipment_id,product_id,yield,unit,factory_id) " +
            "values(#{equipmentId},#{productId},#{yield},#{unit},#{factoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
    void insert(TEquipmentProduct tEquipmentProduct);

    @Delete({"delete from t_equipment_product where id = #{id}"})
    void deleteById(Integer id);

    @Update({
            "update t_equipment_product",
            "set equipment_id=#{equipmentId},product_id=#{productId},yield=#{yield},unit=#{unit}," +
                    "factory_id=#{factoryId}",
            "where id = #{id}"
    })
    int update(TEquipmentProduct tEquipmentProduct);

    // 一对一关联
    @Select({"select * from t_equipment_product where id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="product_id", property="productId"),
            @Result(column="yield", property="yield"),
            @Result(column="unit", property="unit"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="product_id",property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    TEquipmentProduct selectById(Integer id);

    @Select({"select * from t_equipment_product where equipment_id = #{equipmentId} and product_id = #{productId}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="product_id", property="productId"),
            @Result(column="yield", property="yield"),
            @Result(column="unit", property="unit"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="product_id",property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    TEquipmentProduct selectByEquipmentIdAndProductId(TEquipmentProduct tEquipmentProduct);

    @Select({"select * from t_equipment_product where equipment_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="product_id", property="productId"),
            @Result(column="yield", property="yield"),
            @Result(column="unit", property="unit"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="product_id",property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TEquipmentProduct> selectByEquipmentId(Integer id);

    @Select({"select * from t_equipment_product where product_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="product_id", property="productId"),
            @Result(column="yield", property="yield"),
            @Result(column="unit", property="unit"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="product_id",property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TEquipmentProduct> selectByProductId(Integer id);

    @Select({"select * from t_equipment_product where factory_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="product_id", property="productId"),
            @Result(column="yield", property="yield"),
            @Result(column="unit", property="unit"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="product_id",property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TEquipmentProduct> selectByFactoryId(Integer id);
}
