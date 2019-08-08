package com.neu.management.dao;

import com.neu.management.model.TUserRole;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
    @Insert({
            "insert into t_user_role (flag,create_time)",
            "values (#{flag,jdbcType=VARCHAR},#{createTime})"
    })
    int addUserRole(TUserRole userRole);
    }
