package com.neu.management.dao;

import com.neu.management.model.TProductPlan;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductPlanDao {

    // 新增一条生产计划信息
    @Insert("insert into t_product_plan(flag,create_time,create_userid,update_time,update_userid,plan_seq,order_id," +
            "product_id,plan_count,delivery_date,plan_start_date,plan_end_date,plan_status,factory_id) " +
            "values(#{flag},#{createTime},#{createUserid},#{updateTime},#{updateUserid},#{planSeq},#{orderId}," +
            "#{productId},#{planCount},#{deliveryDate},#{planStartDate},#{planEndDate},#{planStatus},#{factoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
    void insert(TProductPlan tProductPlan);

    // 根据ID删除生产计划信息 设置状态为无效
    @Update({"update t_product_plan set update_time=now(),update_userid=#{userId},flag=1 where id = #{id}"})
    void deleteById(Integer id,Integer userId);

    // 更新生产计划信息
    @Update({
            "update t_product_plan",
            "set flag=#{flag},update_time=#{updateTime},update_userid=#{updateUserid},plan_seq=#{planSeq}," +
            "order_id=#{orderId},product_id=#{productId},plan_count=#{planCount},delivery_date=#{deliveryDate}," +
            "plan_start_date=#{planStartDate},plan_end_date=#{planEndDate},plan_status=#{planStatus},factory_id=#{factoryId} ",
            "where id = #{id}"
    })
    int update(TProductPlan tProductPlan);

    // 根据ID获取生产计划信息
    @Select({"select * from t_product_plan where id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="plan_seq", property="planSeq"),
            @Result(column="order_id", property="orderId"),
            @Result(column="product_id", property="productId"),
            @Result(column="plan_count", property="planCount"),
            @Result(column="delivery_date", property="deliveryDate"),
            @Result(column="plan_start_date", property="planStartDate"),
            @Result(column="plan_end_date", property="planEndDate"),
            @Result(column="plan_status", property="planStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="order_id", property="tProductOrder",one=@One(select="com.neu.management.dao.ProductOrderDao.selectById",fetchType= FetchType.EAGER))
    })
    TProductPlan selectById(Integer id);

    @Select({"select * from t_product_plan where factory_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="plan_seq", property="planSeq"),
            @Result(column="order_id", property="orderId"),
            @Result(column="product_id", property="productId"),
            @Result(column="plan_count", property="planCount"),
            @Result(column="delivery_date", property="deliveryDate"),
            @Result(column="plan_start_date", property="planStartDate"),
            @Result(column="plan_end_date", property="planEndDate"),
            @Result(column="plan_status", property="planStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="order_id", property="tProductOrder",one=@One(select="com.neu.management.dao.ProductOrderDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductPlan> selectByFactoryId(Integer id);

    @Select({"select * from t_product_plan where order_id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="plan_seq", property="planSeq"),
            @Result(column="order_id", property="orderId"),
            @Result(column="product_id", property="productId"),
            @Result(column="plan_count", property="planCount"),
            @Result(column="delivery_date", property="deliveryDate"),
            @Result(column="plan_start_date", property="planStartDate"),
            @Result(column="plan_end_date", property="planEndDate"),
            @Result(column="plan_status", property="planStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="order_id", property="tProductOrder",one=@One(select="com.neu.management.dao.ProductOrderDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductPlan> selectByOrderId(Integer id);

    @Select("<script> select * from t_product_plan <where>            "
            + "  <if test='planStatus != null and planStatus != &quot;&quot;'> "
            + "    and plan_status = #{planStatus}                 "
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
            @Result(column="plan_seq", property="planSeq"),
            @Result(column="order_id", property="orderId"),
            @Result(column="product_id", property="productId"),
            @Result(column="plan_count", property="planCount"),
            @Result(column="delivery_date", property="deliveryDate"),
            @Result(column="plan_start_date", property="planStartDate"),
            @Result(column="plan_end_date", property="planEndDate"),
            @Result(column="plan_status", property="planStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="order_id", property="tProductOrder",one=@One(select="com.neu.management.dao.ProductOrderDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductPlan> getAll(TProductPlan tProductPlan);
}
