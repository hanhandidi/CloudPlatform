package com.neu.management.dao;

import com.neu.management.model.TFactory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FactoryDao {

    // 新增工厂信息
    @Insert("insert into t_factory(flag,create_time,create_userid,update_time,update_userid," +
            "bak,factory_name,factory_img_url,factory_addr,factory_url,factory_worker,factory_status) " +
            "values(#{flag},#{createTime},#{createUserid},#{updateTime},#{updateUserid}," +
            "#{bak},#{factoryName},#{factoryImgUrl},#{factoryAddr},#{factoryUrl},#{factoryWorker},#{factoryStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(TFactory tFactory);

    // 根据ID删除一条工厂信息 设置状态为无效
    // @Delete({"delete from t_factory where id = #{id}"})
    @Update({"update t_factory set update_time=now(),update_userid=#{userId},flag = 1,factory_status = 1 where id = #{id}"})
    void deleteById(Integer id,Integer userId);

    // 更新设备信息
    @Update({
            "update t_factory",
            "set flag=#{flag},update_time=#{updateTime},update_userid=#{updateUserid},bak=#{bak}," +
                    "factory_name=#{factoryName},factory_img_url=#{factoryImgUrl},factory_addr=#{factoryAddr}," +
                    "factory_url=#{factoryUrl},factory_worker=#{factoryWorker},factory_status=#{factoryStatus}",
            "where id = #{id}"
    })
    int update(TFactory tFactory);

    // 设置工厂状态
    // 更新设备信息
    @Update({
            "update t_factory",
            "set update_time=#{updateTime},update_userid=#{updateUserid},factory_status=#{factoryStatus} ",
            "where id = #{id}"
    })
    int updateState(TFactory tFactory);

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
    TFactory selectById(Integer id);



    // 判断工厂名称和地址是否同时重复出现
    @Select({"select * from t_factory where factory_name = #{factoryName} and factory_addr= #{factoryAddr}"})
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
    TFactory selectByNameAndAddr(TFactory tFactory);

    // 获得所有工厂信息
    @Select({"select * from t_factory"})
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
    List<TFactory> getAll();

    // 获取所有工厂信息 查询（工厂标识、工厂状态、工厂名称(模糊)、工厂地址(模糊)）
    @Select("<script> select * from t_factory <where>              "
            + "  <if test='flag != null and flag != &quot;&quot;'>     "
            + "    and flag = #{flag}                              "
            + "  </if>                                             "
            + "  <if test='factoryName != null and factoryName != &quot;&quot;'> "
            + "    and factory_name like CONCAT('%', #{factoryName}, '%')        "
            + "  </if>                                                           "
            + "  <if test='factoryAddr != null and factoryAddr != &quot;&quot;'> "
            + "    and factory_addr like CONCAT('%', #{factoryAddr}, '%')        "
            + "  </if>                                                           "
            + "  <if test='factoryStatus != null and factoryStatus != &quot;&quot;'> "
            + "    and factory_status = #{factoryStatus}                         "
            + "  </if>                                                           "
            + "</where> </script>")
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
    List<TFactory> selectAll(TFactory tFactory);
}
