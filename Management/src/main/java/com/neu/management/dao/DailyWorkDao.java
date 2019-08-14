package com.neu.management.dao;

import com.neu.management.model.TDailyWork;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DailyWorkDao {

    // 新增一条报工信息
    @Insert("insert into t_daily_work(flag,create_time,create_userid,update_time,update_userid," +
            "schedule_id,equipment_id,equipment_seq,start_time,end_time,working_count,qualified_count," +
            "unqualified_cout,complete_flag,factory_id,bak) " +
            "values(#{flag},#{createTime},#{createUserid},#{updateTime},#{updateUserid},#{scheduleId}," +
            "#{equipmentId},#{equipmentSeq},#{startTime},#{endTime},#{workingCount},#{qualifiedCount}," +
            "#{unqualifiedCout},#{completeFlag},#{factoryId},#{bak})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
    void insert(TDailyWork tDailyWork);

    // 根据ID删除生产调度信息 设置状态为无效
    @Update({"update t_daily_work set update_time=now(),update_userid=#{userId},flag=1 where id = #{id}"})
    void deleteById(Integer id,Integer userId);

    // 更新生产调度信息
    @Update({
            "update t_daily_work",
            "set update_time=#{updateTime},update_userid=#{updateUserid},complete_flag=#{completeFlag} ",
            "where id = #{id}"
    })
    int update(TDailyWork tDailyWork);

    // 根据ID获取生产调度信息
    @Select({"select * from t_daily_work where id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_id", property="scheduleId"),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="start_time", property="startTime"),
            @Result(column="end_time", property="endTime"),
            @Result(column="working_count", property="workingCount"),
            @Result(column="qualified_count", property="qualifiedCount"),
            @Result(column="unqualified_cout", property="unqualifiedCout"),
            @Result(column="complete_flag", property="completeFlag"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="bak", property="bak"),
            @Result(column="schedule_id",property="tProductSchedule",one=@One(select="com.neu.management.dao.ProductScheduleDao.selectById",fetchType= FetchType.EAGER))
    })
    TDailyWork selectById(Integer id);

    @Select({"select * from t_daily_work where factory_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_id", property="scheduleId"),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="start_time", property="startTime"),
            @Result(column="end_time", property="endTime"),
            @Result(column="working_count", property="workingCount"),
            @Result(column="qualified_count", property="qualifiedCount"),
            @Result(column="unqualified_cout", property="unqualifiedCout"),
            @Result(column="complete_flag", property="completeFlag"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="bak", property="bak"),
            @Result(column="schedule_id",property="tProductSchedule",one=@One(select="com.neu.management.dao.ProductScheduleDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TDailyWork> selectByFactoryId(Integer id);

    @Select("<script> select * from t_equipment <where>            "
            + "  <if test='equipmentId != null and equipmentId != &quot;&quot;'>     "
            + "    and equipment_id = #{equipmentId}               "
            + "  </if>                                             "
            + "  <if test='equipmentName != null and equipmentName != &quot;&quot;'> "
            + "    and complete_flag = #{completeFlag}             "
            + "  </if>                                             "
            + "  <if test='factoryId != null and factoryId != &quot;&quot;'> "
            + "    and factory_id = #{factoryId})                  "
            + "  </if>                                             "
            + "</where> </script>")
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_id", property="scheduleId"),
            @Result(column="equipment_id", property="equipmentId"),
            @Result(column="equipment_seq", property="equipmentSeq"),
            @Result(column="start_time", property="startTime"),
            @Result(column="end_time", property="endTime"),
            @Result(column="working_count", property="workingCount"),
            @Result(column="qualified_count", property="qualifiedCount"),
            @Result(column="unqualified_cout", property="unqualifiedCout"),
            @Result(column="complete_flag", property="completeFlag"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="bak", property="bak"),
            @Result(column="schedule_id",property="tProductSchedule",one=@One(select="com.neu.management.dao.ProductScheduleDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TDailyWork> getAll(TDailyWork tDailyWork);
}
