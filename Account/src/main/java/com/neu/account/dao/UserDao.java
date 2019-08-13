package com.neu.account.dao;

import com.neu.account.entity.TUser;
import org.apache.ibatis.annotations.*;

public interface UserDao {

    // 根据ID获取用户
    @Select("SELECT * FROM t_user WHERE id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "createUserid", column = "create_userid"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "updateUserid", column = "update_userid"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userRealName", column = "user_real_name"),
            @Result(property = "userPasswd", column = "user_passwd"),
            @Result(property = "userJobNum", column = "user_job_num"),
            @Result(property = "userPhoneNum", column = "user_phone_num"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "factoryId", column = "factory_id")
    })
    TUser getUserById(String userId);

    // 根据name获取用户
    @Select("SELECT * FROM t_user WHERE user_name = #{userName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "createUserid", column = "create_userid"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "updateUserid", column = "update_userid"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userRealName", column = "user_real_name"),
            @Result(property = "userPasswd", column = "user_passwd"),
            @Result(property = "userJobNum", column = "user_job_num"),
            @Result(property = "userPhoneNum", column = "user_phone_num"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "factoryId", column = "factory_id")
    })
    TUser getUserByName(String userName);

    // 添加用户
    @Insert("INSERT t_user (flag, create_userid, update_userid, user_name, user_real_name," +
            " user_passwd, user_job_num, user_phone_num, user_email, role_id, factory_id)" +
            " VALUES (#{flag}, #{createUserid}, #{updateUserid}, #{userName}, #{userRealName}," +
            " #{userPasswd}, #{userJobNum}, #{userPhoneNum}, #{userEmail}, #{roleId}, #{factoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void setUserInfo(TUser user);

    // 更新用户
    @Update("UPDATE t_user SET flag = #{flag}, create_userid = #{createUserid}, update_userid = #{updateUserid}," +
            " user_status = #{userStatus}, user_name = #{userName}, user_real_name = #{userRealName}," +
            " user_passwd = #{userPasswd}, user_job_num = #{userJobNum}, user_phone_num = #{userPhoneNum}," +
            " user_email = #{userEmail}, role_id = #{roleId}, factory_id = #{factoryId} WHERE id = #{id}")
    void updateUserInfo(TUser user);


    //http://localhost/user/update?id=4&userName=hhh&userRealName=jcy&userPasswd=123456&userJobNum=1&userPhoneNum=123456&userEmail=1@2.3&roleId=9&factoryId=5
}
