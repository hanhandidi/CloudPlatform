package com.neu.management.dao;

import com.neu.management.model.TProductSchedule;
import com.neu.management.modelVO.ProductScheduleVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductScheduleDao {

    // 新增一条生产调度信息
    @Insert("insert into t_product_schedule(flag,create_time,create_userid,update_time,update_userid," +
            "schedule_seq,schedule_count,schedule_status,plan_id,product_id,equipment_id,start_date,end_date,factory_id) " +
            "values(#{flag},#{createTime},#{createUserid},#{updateTime},#{updateUserid}," +
            "#{scheduleSeq},#{scheduleCount},#{scheduleStatus},#{planId},#{productId},#{equipmentId},#{startDate},#{endDate},#{factoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
    void insert(TProductSchedule tProductSchedule);

    // 根据ID删除生产调度信息 设置状态为无效
    // @Update({"update t_product_schedul set update_time=now(),update_userid=#{userId},flag=1 where id = #{id}"})
    @Delete({"delete from t_product_schedul where id = #{id}"})
    void deleteById(Integer id);

    // 更新生产调度信息
    @Update({
            "update t_product_schedule",
            "set update_time=#{updateTime},update_userid=#{updateUserid},schedule_count=#{scheduleCount}," +
                    "equipment_id=#{equipmentId},start_date=#{startDate},end_date=#{endDate}",
            "where id = #{id}"
    })
    int update(ProductScheduleVO productScheduleVO);

    @Update({"update t_product_schedule set update_time=#{updateTime},update_userid=#{updateUserid},schedule_status = #{scheduleStatus} where id = #{id}"})
    int updateState(TProductSchedule tProductSchedule);

    // 根据ID获取生产调度信息
    @Select({"select * from t_product_schedule where id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_seq", property="scheduleSeq"),
            @Result(column="schedule_count", property="scheduleCount"),
            @Result(column="schedule_status", property="scheduleStatus"),
            @Result(column="plan_id", property="planId"),
            @Result(column="product_id", property="productId"),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="start_date", property="startDate"),
            @Result(column="end_date", property="endDate"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="equipment_id", property="tEquipment",one=@One(select="com.neu.management.dao.EquipmentDao.selectById",fetchType= FetchType.EAGER)),
            @Result(column="plan_id", property="tProductPlan",one=@One(select="com.neu.management.dao.ProductPlanDao.selectById",fetchType= FetchType.EAGER))
    })
    TProductSchedule selectById(Integer id);

    // 获取工厂所有的生成调度信息
    @Select({"select * from t_product_schedule where factory_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_seq", property="scheduleSeq"),
            @Result(column="schedule_count", property="scheduleCount"),
            @Result(column="schedule_status", property="scheduleStatus"),
            @Result(column="plan_id", property="planId"),
            @Result(column="product_id", property="productId"),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="start_date", property="startDate"),
            @Result(column="end_date", property="endDate"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="equipment_id", property="tEquipment",one=@One(select="com.neu.management.dao.EquipmentDao.selectById",fetchType= FetchType.EAGER)),
            @Result(column="plan_id", property="tProductPlan",one=@One(select="com.neu.management.dao.ProductPlanDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductSchedule> selectByFactoryId(Integer id);

    @Select({"select * from t_product_schedule where plan_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_seq", property="scheduleSeq"),
            @Result(column="schedule_count", property="scheduleCount"),
            @Result(column="schedule_status", property="scheduleStatus"),
            @Result(column="plan_id", property="planId"),
            @Result(column="product_id", property="productId"),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="start_date", property="startDate"),
            @Result(column="end_date", property="endDate"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="equipment_id", property="tEquipment",one=@One(select="com.neu.management.dao.EquipmentDao.selectById",fetchType= FetchType.EAGER)),
            @Result(column="plan_id", property="tProductPlan",one=@One(select="com.neu.management.dao.ProductPlanDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductSchedule> selectByPlanId(Integer id);

    @Select({"<script> select * from t_product_schedule <where>            "
            + "  <if test='scheduleStatus != null and scheduleStatus != &quot;&quot;'> "
            + "    and schedule_status = #{scheduleStatus}         "
            + "  </if>                                             "
            + "  <if test='planId != null and planId != &quot;&quot;'> "
            + "    and plan_id = #{planId}                         "
            + "  </if>                                             "
            + "  <if test='productId != null and productId != &quot;&quot;'> "
            + "    and product_id = #{productId}                   "
            + "  </if>                                             "
            + "  <if test='equipmentId != null and equipmentId != &quot;&quot;'> "
            + "    and equipment_id = #{equipmentId}               "
            + "  </if>                                             "
            + "  <if test='factoryId != null and factoryId != &quot;&quot;'> "
            + "    and factory_id = #{factoryId})                  "
            + "  </if>                                             "
            + "</where> </script>"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_seq", property="scheduleSeq"),
            @Result(column="schedule_count", property="scheduleCount"),
            @Result(column="schedule_status", property="scheduleStatus"),
            @Result(column="plan_id", property="planId"),
            @Result(column="product_id", property="productId"),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="start_date", property="startDate"),
            @Result(column="end_date", property="endDate"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="equipment_id", property="tEquipment",one=@One(select="com.neu.management.dao.EquipmentDao.selectById",fetchType= FetchType.EAGER)),
            @Result(column="plan_id", property="tProductPlan",one=@One(select="com.neu.management.dao.ProductPlanDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductSchedule> getAll(TProductSchedule tProductSchedule);
}
