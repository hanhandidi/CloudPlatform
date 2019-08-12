package com.neu.account.dao;

import com.neu.account.entity.TUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
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
    TUser getUserInfo(String userName);
}
