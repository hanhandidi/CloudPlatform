package com.neu.management.dao;

import com.neu.management.model.TUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface TUserDao {

    @Select({"select * from t_user where id = #{id}"})
    @Results({
            @Result(column ="id",property = "id"),
            @Result(column ="flag",property = "flag"),
            @Result(column ="create_time",property = "createTime"),
            @Result(column ="create_userid",property = "createUserid"),
            @Result(column ="update_time",property = "updateTime"),
            @Result(column ="update_userid",property = "updateUserid"),
            @Result(column ="user_status",property = "userStatus"),
            @Result(column ="user_name",property = "userName"),
            @Result(column ="user_real_name",property = "userRealName"),
            @Result(column ="user_passwd",property = "userPasswd"),
            @Result(column ="user_job_num",property = "userJobNum"),
            @Result(column ="user_phone_num",property = "userPhoneNum"),
            @Result(column ="user_email",property = "userEmail"),
            @Result(column ="role_id",property = "roleId"),
            @Result(column ="factory_id",property = "factoryId")
    })
    TUser selectById(Integer id);

    @Select({"select * from t_user where user_name= #{userName}"+
            " and user_passwd =#{userPasswd} "})
    TUser selectByNameAndPwd(TUser tUser);
}
