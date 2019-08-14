package com.neu.management.dao;

import com.neu.management.model.TOrderTrack;
import com.neu.management.modelVO.OrderTrackVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface OrderTrackDao {

    // 新增一条订单跟踪信息
    @Insert("insert into t_order_track(flag,create_time,create_userid,update_time,update_userid," +
            "schedule_id,plan_id,product_id,working_count,qualified_count,factory_id) " +
            "values(#{flag},#{createTime},#{createUserid},#{updateTime},#{updateUserid}," +
            "#{scheduleId},#{planId},#{productId},#{workingCount},#{qualifiedCount},#{factoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
    void insert(TOrderTrack tOrderTrack);

    // 根据ID删除订单跟踪信息 设置状态为无效
    // @Update({"update t_order_track set update_time=now(),update_userid=#{userId},flag=1 where id = #{id}"})
    @Delete({"delete from t_order_track where id = #{id}"})
    void deleteById(Integer id);

    // 更新订单跟踪信息
    @Update({
            "update t_order_track",
            "set update_time=#{updateTime},update_userid=#{updateUserid},working_count=#{workingCount},qualified_count=#{qualifiedCount} ",
            "where id = #{id}"
    })
    int update(OrderTrackVO orderTrackVO);

    // 根据ID获取订单跟踪信息
    @Select({"select * from t_order_track where id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_id", property="scheduleId"),
            @Result(column="plan_id", property="planId"),
            @Result(column="product_id", property="productId"),
            @Result(column="working_count", property="workingCount"),
            @Result(column="qualified_count", property="qualifiedCount"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="schedule_id",property="tProductSchedule",one=@One(select="com.neu.management.dao.ProductScheduleDao.selectById",fetchType= FetchType.EAGER))
    })
    TOrderTrack selectById(Integer id);

    @Select({"select * from t_order_track where factory_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="schedule_id", property="scheduleId"),
            @Result(column="plan_id", property="planId"),
            @Result(column="product_id", property="productId"),
            @Result(column="working_count", property="workingCount"),
            @Result(column="qualified_count", property="qualifiedCount"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="schedule_id",property="tProductSchedule",one=@One(select="com.neu.management.dao.ProductScheduleDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TOrderTrack> selectByFactoryId(Integer id);

    @Select("<script> select * from t_order_track <where>            "
            + "  <if test='planId != null and planId != &quot;&quot;'> "
            + "    and plan_id = #{planId}                         "
            + "  </if>                                             "
            + "  <if test='productId != null and productId != &quot;&quot;'> "
            + "    and product_id = #{productId}                         "
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
            @Result(column="plan_id", property="planId"),
            @Result(column="product_id", property="productId"),
            @Result(column="working_count", property="workingCount"),
            @Result(column="qualified_count", property="qualifiedCount"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="schedule_id",property="tProductSchedule",one=@One(select="com.neu.management.dao.ProductScheduleDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TOrderTrack> getAll(TOrderTrack tOrderTrack);
}
