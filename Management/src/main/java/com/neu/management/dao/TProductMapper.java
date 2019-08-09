package com.neu.management.dao;

import com.neu.management.model.TProduct;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TProductMapper {

    @SelectProvider(type=TProductSqlProvider.class, method="selectProducts")
    @Results({
            @Result(column="id", property="id" ,id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime"),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="product_num", property="productNum"),
            @Result(column="product_name", property="productName"),
            @Result(column="product_img_url", property="productImgUrl"),
            @Result(column="factory_id", property="factoryId"),
    })
    List<TProduct> selectProducts(TProduct record);

    @Select({
            "select * from t_product where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id",  id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="product_num", property="productNum"),
            @Result(column="product_name", property="productName"),
            @Result(column="product_img_url", property="productImgUrl"),
            @Result(column="factory_id", property="factoryId"),
    })
    TProduct selectById(Integer id);

    @Select({
            "select * from t_product where product_num = #{num,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="product_num", property="productNum"),
            @Result(column="product_name", property="productName"),
            @Result(column="product_img_url", property="productImgUrl"),
            @Result(column="factory_id", property="factoryId"),
    })
    TProduct selectByNum(String num);

    @Select({
            "select * from t_product where product_name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id",  id=true),
            @Result(column="flag", property="flag"),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="create_userid", property="createUserid"),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="update_userid", property="updateUserid"),
            @Result(column="product_num", property="productNum"),
            @Result(column="product_name", property="productName"),
            @Result(column="product_img_url", property="productImgUrl"),
            @Result(column="factory_id", property="factoryId"),
    })
    TProduct selectByName(String name);



    @Insert({
            "insert into t_product ( flag, create_time," +
                    "create_userid, update_time, update_userid,product_num, product_name, product_img_url," +
                    "factory_id)" +
                    "values ( #{flag,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}," +
                    "#{createUserid,jdbcType=INTEGER}, #{updateTime,jdbcType=TIMESTAMP}, #{updateUserid,jdbcType=INTEGER}," +
                    "#{productNum,jdbcType=VARCHAR}, #{productName,jdbcType=VARCHAR}, #{productImgUrl,jdbcType=VARCHAR}," +
                    "#{factoryId,jdbcType=INTEGER})"
    })
    int addProduct(TProduct record);
    @Update({
            "update t_product " +
                    "set flag = #{flag,jdbcType=INTEGER}," +
                    "create_time = #{createTime,jdbcType=TIMESTAMP}," +
                    "create_userid = #{createUserid,jdbcType=INTEGER}," +
                    "update_time = #{updateTime,jdbcType=TIMESTAMP}," +
                    "update_userid = #{updateUserid,jdbcType=INTEGER}," +
                    "product_num = #{productNum,jdbcType=VARCHAR}," +
                    "product_name = #{productName,jdbcType=VARCHAR}," +
                    "product_img_url = #{productImgUrl,jdbcType=VARCHAR}," +
                    "factory_id = #{factoryId,jdbcType=INTEGER} " +
                    "where id = #{id,jdbcType=INTEGER}"
    })
    int updateProduct(TProduct record);


    @DeleteProvider(type = TProductSqlProvider.class,method = "deleteProductByIds")
    int deleteProductsByIds(Map<String,List<Integer>> map);

    @Delete({"delete from t_product where id =#{id}"})
    int deleteById(Integer id);
}
