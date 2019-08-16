package com.neu.management.dao;

import com.neu.management.model.TProductOrder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductOrderDao {

    // 新增订单
    @Insert("insert into t_product_order(flag,create_time,create_userid,update_time,update_userid," +
            "order_seq,order_source,product_id,product_count,end_date,order_status,factory_id,bak) " +
            "values(#{flag},#{createTime},#{createUserid},#{updateTime},#{updateUserid}," +
            "#{orderSeq},#{orderSource},#{productId},#{productCount},#{endDate},#{orderStatus},#{factoryId},#{bak})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
    void insert(TProductOrder tProductOrder);

    // 根据ID删除订单信息 设置状态为无效
    // @Update({"update t_product_order set update_time=now(),update_userid=#{userId},flag = 1 where id = #{id}"})
    @Delete({"delete from t_product_order where id = #{id}"})
    void deleteById(Integer id);

    // 更新订单信息
    @Update({
            "update t_product_order",
            "set flag=#{flag},update_time=#{updateTime},update_userid=#{updateUserid}," +
                    "order_source=#{orderSource},product_id=#{productId},product_count=#{productCount}," +
                    "end_date=#{endDate},order_status=#{orderStatus},factory_id=#{factoryId},bak=#{bak} ",
            "where id = #{id}"
    })
    int update(TProductOrder tProductOrder);

    @Update({
            "update t_product_order",
            "set update_time=#{updateTime},update_userid=#{updateUserid},order_status=#{orderStatus},bak=#{bak}",
            "where id = #{id}"
    })
    int updateState(TProductOrder tProductOrder);

    // 根据ID获取订单信息
    @Select({"select * from t_product_order where id = #{id}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="order_seq", property="orderSeq"),
            @Result(column="order_source", property="orderSource"),
            @Result(column="product_id", property="productId"),
            @Result(column="product_count", property="productCount"),
            @Result(column="end_date", property="endDate"),
            @Result(column="order_status", property="orderStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="bak", property="bak"),
            @Result(column="product_id", property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    TProductOrder selectById(Integer id);

    // 已接单, 生产中、状态下不能删除
    @Select({"select * from t_product_order where id = #{id} and order_status in (20,40)"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="order_seq", property="orderSeq"),
            @Result(column="order_source", property="orderSource"),
            @Result(column="product_id", property="productId"),
            @Result(column="product_count", property="productCount"),
            @Result(column="end_date", property="endDate"),
            @Result(column="order_status", property="orderStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="bak", property="bak"),
            @Result(column="product_id", property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    TProductOrder isCanDelete(Integer id);

    // 检查该工厂是否能够生产该产品
    @Select({"SELECT t_product_order.* from t_product_order INNER JOIN t_product ON " +
            "t_product_order.product_id = t_product.id WHERE " +
            "t_product_order.factory_id = #{factoryId}"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="order_seq", property="orderSeq"),
            @Result(column="order_source", property="orderSource"),
            @Result(column="product_id", property="productId"),
            @Result(column="product_count", property="productCount"),
            @Result(column="end_date", property="endDate"),
            @Result(column="order_status", property="orderStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="bak", property="bak"),
            @Result(column="product_id", property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductOrder> haveThisProductInFactory(TProductOrder tProductOrder);

    @Select({"select * from t_product_order where factory_id = #{id} order by order_status ASC"})
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="order_seq", property="orderSeq"),
            @Result(column="order_source", property="orderSource"),
            @Result(column="product_id", property="productId"),
            @Result(column="product_count", property="productCount"),
            @Result(column="end_date", property="endDate"),
            @Result(column="order_status", property="orderStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="bak", property="bak"),
            @Result(column="product_id", property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductOrder> selectByFactoryId(Integer id);

    @Select("<script> select * from t_product_order <where>            "
            + "  <if test='orderStatus != null and orderStatus != &quot;&quot;'> "
            + "    and order_status = #{orderStatus}               "
            + "  </if>                                             "
            + "  <if test='productId != null and productId != &quot;&quot;'> "
            + "    and product_id = #{productId}                   "
            + "  </if>                                             "
            + "  <if test='factoryId != null and factoryId != &quot;&quot;'> "
            + "    and factory_id = #{factoryId}                   "
            + "  </if>                                             "
            + "</where> </script>")
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="order_seq", property="orderSeq"),
            @Result(column="order_source", property="orderSource"),
            @Result(column="product_id", property="productId"),
            @Result(column="product_count", property="productCount"),
            @Result(column="end_date", property="endDate"),
            @Result(column="order_status", property="orderStatus"),
            @Result(column="factory_id", property="factoryId"),
            @Result(column="bak", property="bak"),
            @Result(column="product_id", property="tProduct",one=@One(select="com.neu.management.dao.ProductDao.selectById",fetchType= FetchType.EAGER))
    })
    List<TProductOrder> getAll(TProductOrder tProductOrder);
}
