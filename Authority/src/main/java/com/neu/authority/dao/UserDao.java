package com.neu.authority.dao;

import com.neu.authority.entity.TUser;
import com.neu.authority.entity.TUserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    // 根据ID获取用户（供一对一查询，非传入user）
    @Select("SELECT * FROM t_user WHERE id = #{id}")
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
    TUser selectUserByIdInDB(long id);

    // 根据ID获取用户
    @Select("SELECT * FROM t_user WHERE id = #{id}")
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
    TUser selectUserById(TUser user);

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
    TUser selectUserByName(TUser user);

    // 添加用户
    @Insert("INSERT t_user (create_userid, update_userid, user_name, user_real_name," +
            " user_passwd, user_job_num, user_phone_num, user_email, role_id, factory_id)" +
            " VALUES (#{createUserid}, #{updateUserid}, #{userName}, #{userRealName}," +
            " #{userPasswd}, #{userJobNum}, #{userPhoneNum}, #{userEmail}, #{roleId}, #{factoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(TUser user);

    // 删除用户
    @Update({"UPDATE t_user" +
            " SET update_userid = #{updateUserid}, flag = 1" +
            " WHERE id = #{id}"})
    int deleteUser(TUser user);

    // 更新用户
    @Update("UPDATE t_user SET user_status = #{userStatus}, role_id = #{roleId}," +
            " create_userid = #{createUserid}, update_userid = #{updateUserid}," +
            " user_name = #{userName}, user_real_name = #{userRealName}," +
            " user_passwd = #{userPasswd}, user_job_num = #{userJobNum}," +
            " user_phone_num = #{userPhoneNum}, user_email = #{userEmail}, factory_id = #{factoryId} WHERE id = #{id}")
    int updateUser(TUser user);

    // 获取用户列表（工厂ID）
    @Select("SELECT * FROM t_user WHERE factory_id = #{factoryId} and flag = 0")
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
            @Result(property = "factoryId", column = "factory_id"),
            @Result(property = "createUser", column = "create_userid"
                    , javaType = TUser.class, one = @One(select = "com.neu.authority.dao.UserDao.selectUserByIdInDB")),
            @Result(property = "updateUser", column = "update_userid"
                    , javaType = TUser.class, one = @One(select = "com.neu.authority.dao.UserDao.selectUserByIdInDB")),
            @Result(property = "userRole", column = "role_id"
                    , javaType = TUserRole.class, one = @One(select = "com.neu.authority.dao.RoleDao.selectRoleByIdInDB"))
    })
    List<TUser> selectUsersInFactory(TUser user);
}
