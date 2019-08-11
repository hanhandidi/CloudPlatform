package com.neu.management.dao;

import com.neu.management.model.TEquipment;
import com.neu.management.modelVO.EquipmentSelectVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface EquipmentDao {

    // 新增一条设备
    @Insert("insert into t_equipment(flag,create_time,create_userid,update_time,update_userid," +
            "equipment_seq,equipment_name,equipment_img_url,equipment_status,factory_id) " +
            "values(#{flag},#{createTime},#{createUserid},#{updateTime},#{updateUserid}," +
            "#{equipmentSeq},#{equipmentName},#{equipmentImgUrl},#{equipmentStatus},#{factoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id") // 添加成功之后将主键值赋值给tEquipment id
    void insert(TEquipment tEquipment);

    // 批量添加
    @InsertProvider(type = Provider.class, method = "insertBatch")
    int insertBatch(List<TEquipment> tEquipments);

    // 根据ID删除一条设备
    @Delete({"delete from t_equipment where id = #{id}"})
    void deleteById(Integer id);

    // 批量删除
    @DeleteProvider(type = Provider.class, method = "deleteBatch")
    void deleteBatch(List<Integer> ids);

    // 删除所有设备
    @Delete("delete from t_equipment")
    void deleteAll();

    // 更新设备信息
    @Update({
            "update t_equipment",
            "set flag=#{flag},update_time=#{updateTime},update_userid=#{updateUserid},equipment_seq=#{equipmentSeq}," +
                    "equipment_name=#{equipmentName},equipment_img_url=#{equipmentImgUrl},equipment_status=#{equipmentStatus}," +
                    "factory_id=#{factoryId}",
            "where id = #{id}"
    })
    int update(TEquipment record);

    // 检查该设备ID是否有已启动的工单
    @Select({"select t_equipment.* from t_equipment INNER JOIN t_equipment_product on " +
            "t_equipment.id = t_equipment_product.equipment_id INNER JOIN t_product_plan on " +
            "t_equipment_product.product_id = t_product_plan.product_id WHERE t_equipment.id = #{id} " +
            "and t_product_plan.plan_status = 20 AND t_equipment.factory_id = t_equipment_product.factory_id " +
            "AND t_equipment.factory_id = t_product_plan.factory_id"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="equipment_name", property="equipmentName"),
            @Result(column="equipment_img_url", property="equipmentImgUrl"),
            @Result(column="equipment_status", property="equipmentStatus"),
            @Result(column="factory_id", property="factoryId")
    })
    TEquipment isInPlaned(Integer id);


    // 根据ID获取设备信息
    @Select({"select * from t_equipment where id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="equipment_name", property="equipmentName"),
            @Result(column="equipment_img_url", property="equipmentImgUrl"),
            @Result(column="equipment_status", property="equipmentStatus"),
            @Result(column="factory_id", property="factoryId")
    })
    TEquipment selectById(Integer id);

    // 获取所有设备信息 查询（设备状态、设备名称(模糊)）
    @Select("<script> select * from t_equipment <where>            "
            + "  <if test='equipmentStatus != null and equipmentStatus != &quot;&quot;'>     "
            + "    and equipment_status = #{id}                    "
            + "  </if>                                             "
            + "  <if test='equipmentName != null and equipmentName != &quot;&quot;'> "
            + "    and equipment_name like CONCAT('%', #{equipmentName}, '%')        "
            + "  </if>                                             "
            + "</where> </script>")
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="equipment_name", property="equipmentName"),
            @Result(column="equipment_img_url", property="equipmentImgUrl"),
            @Result(column="equipment_status", property="equipmentStatus"),
            @Result(column="factory_id", property="factoryId")
    })
    List<TEquipment> selectAll(TEquipment tEquipment);

    @Select({"select t_equipment.* FROM t_equipment INNER JOIN t_equipment_product " +
            "on t_equipment.id = t_equipment_product.equipment_id " +
            "INNER JOIN t_product on t_equipment_product.product_id = t_product.id " +
            "INNER JOIN t_factory on t_product.factory_id = t_factory.id " +
            "where t_equipment.equipment_name = #{equipmentName} AND t_product.product_name = #{productName} AND t_factory.id = #{factoryId}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="equipment_name", property="equipmentName"),
            @Result(column="equipment_img_url", property="equipmentImgUrl"),
            @Result(column="equipment_status", property="equipmentStatus"),
            @Result(column="factory_id", property="factoryId")
    })
    List<TEquipment> getAll(EquipmentSelectVO equipmentSelectVO);

    // 根据序列号以及工厂ID获取设备信息  同一工厂序列号不能重复
    @Select({"select * from t_equipment where equipment_seq = #{equipmentSeq} and factory_id = #{factoryId}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="equipment_name", property="equipmentName"),
            @Result(column="equipment_img_url", property="equipmentImgUrl"),
            @Result(column="equipment_status", property="equipmentStatus"),
            @Result(column="factory_id", property="factoryId")
    })
    TEquipment selectBySeqAndFactoryId(TEquipment tEquipment);

    // 根据工厂ID获取设备信息
    @Select({"select * from t_equipment where factory_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="equipment_name", property="equipmentName"),
            @Result(column="equipment_img_url", property="equipmentImgUrl"),
            @Result(column="equipment_status", property="equipmentStatus"),
            @Result(column="factory_id", property="factoryId")
    })
    List<TEquipment> selectByFactoryId(Integer id);

    class Provider {
        /* 批量插入 */
        public String insertBatch(Map map) {
            List<TEquipment> tEquipments = (List<TEquipment>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO t_equipment (flag,create_time,create_userid,update_time,update_userid," +
                    "equipment_seq,equipment_name,equipment_img_url,equipment_status,factory_id) VALUES ");
            MessageFormat mf = new MessageFormat(
                    "(#'{'list[{0}].flag}, #'{'list[{0}].createTime}, #'{'list[{0}].createUserid}, " +
                            "#'{'list[{0}].updateTime}, #'{'list[{0}].updateUserid}, #'{'list[{0}].equipmentSeq}," +
                            "#'{'list[{0}].equipmentName}, #'{'list[{0}].equipmentImgUrl}, " +
                            "#'{'list[{0}].equipmentStatus}, #'{'list[{0}].factoryId})"
            );
            for (int i = 0; i < tEquipments.size(); i++) {
                sb.append(mf.format(new Object[] {i}));
                if (i < tEquipments.size() - 1)
                    sb.append(",");
            }
            return sb.toString();
        }

        /* 批量删除 */
        public String deleteBatch(Map map) {
            List<Integer> ids = (List<Integer>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM t_equipment WHERE id IN (");
            for (int i = 0; i < ids.size(); i++) {
                sb.append("'").append(ids.get(i)).append("'");
                if (i < ids.size() - 1)
                    sb.append(",");
            }
            sb.append(")");
            return sb.toString();
        }
    }
}
