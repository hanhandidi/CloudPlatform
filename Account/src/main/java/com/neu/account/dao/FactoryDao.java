package com.neu.account.dao;

import com.neu.account.entity.TFactory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface FactoryDao {

    // 新增工厂信息
    @Insert("INSERT INTO t_factory (flag, create_userid, update_userid, bak, factory_name," +
            " factory_img_url, factory_addr, factory_url ,factory_worker, factory_status)" +
            " VALUES (#{flag}, #{createUserid}, #{updateUserid}, #{bak}, #{factoryName}," +
            " #{factoryImgUrl}, #{factoryAddr}, #{factoryUrl}, #{factoryWorker}, #{factoryStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addFactory(TFactory factory);


    // 根据ID获取工厂信息
    @Select({"select * from t_factory where id = #{id}"})
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "flag", property = "flag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_userid", property = "createUserid"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_userid", property = "updateUserid"),
            @Result(column = "bak", property = "bak"),
            @Result(column = "factory_name", property = "factoryName"),
            @Result(column = "factory_img_url", property = "factoryImgUrl"),
            @Result(column = "factory_addr", property = "factoryAddr"),
            @Result(column = "factory_url", property = "factoryUrl"),
            @Result(column = "factory_worker", property = "factoryWorker"),
            @Result(column = "factory_status", property = "factoryStatus")
    })
    TFactory getById(Integer factoryId);

    // 根据name获取工厂信息
    @Select({"select * from t_factory where factory_name = #{factoryName}"})
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "flag", property = "flag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_userid", property = "createUserid"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_userid", property = "updateUserid"),
            @Result(column = "bak", property = "bak"),
            @Result(column = "factory_name", property = "factoryName"),
            @Result(column = "factory_img_url", property = "factoryImgUrl"),
            @Result(column = "factory_addr", property = "factoryAddr"),
            @Result(column = "factory_url", property = "factoryUrl"),
            @Result(column = "factory_worker", property = "factoryWorker"),
            @Result(column = "factory_status", property = "factoryStatus")
    })
    TFactory getByName(String factoryName);
    //http://localhost/factory/create?factoryName=测试&factoryImgUrl=123456&factoryAddr=江苏南京&factoryUrl=123456&factoryWorker=1

}
