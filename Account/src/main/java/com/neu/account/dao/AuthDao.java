package com.neu.account.dao;

import com.neu.account.entity.TAuth;
import org.apache.ibatis.annotations.*;

public interface AuthDao {
    @Insert("INSERT INTO t_auth (auth_token, auth_ip, auth_address)" +
            "VALUES (#{authToken}, #{authIp}, #{authAddress})")
    void addAuthInfo(String authToken, String authIp, String authAddress);

    @Select("SELECT * FROM t_auth WHERE auth_token = #{authToken}")
    @Results({
            @Result(property = "authToken", column = "auth_token"),
            @Result(property = "authTime", column = "auth_time"),
            @Result(property = "authIp", column = "auth_ip"),
            @Result(property = "authAddress", column = "auth_address"),
            @Result(property = "authState", column = "auth_state"),
            @Result(property = "userId", column = "user_id")
    })
    TAuth getAuthInfo(String authToken);

    @Update("UPDATE t_auth SET auth_state = #{authState}, user_id = #{userId} WHERE auth_token = #{authToken}")
    void setAuthState(String authToken, Integer authState, String userId);
}
