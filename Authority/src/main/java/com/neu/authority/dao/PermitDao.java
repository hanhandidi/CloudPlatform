package com.neu.authority.dao;

import com.neu.authority.entity.TPermit;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermitDao {

    // 通过ID获得权限/菜单信息
    @Select("SELECT * FROM t_permit WHERE id = #{permitId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "status", property = "status"),
            @Result(column = "permit_name", property = "permitName"),
            @Result(column = "permit_path", property = "permitPath"),
            @Result(column = "permit_icon", property = "permitIcon"),
            @Result(column = "permit_desc", property = "permitDesc"),
            @Result(column = "parent_id", property = "parentId")
    })
    TPermit selectPermitById(TPermit permit);

    // 获得所有权限/菜单（结构）
    @Select("SELECT * FROM t_permit WHERE parent_id = #{permitId} AND flag = 0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "status", property = "status"),
            @Result(column = "permit_name", property = "permitName"),
            @Result(column = "permit_path", property = "permitPath"),
            @Result(column = "permit_icon", property = "permitIcon"),
            @Result(column = "permit_desc", property = "permitDesc"),
            @Result(column = "id", property = "permitSubs",
                    javaType = List.class, many = @Many(select = "com.neu.authority.dao.PermitDao.getAllMenuPermits"))
    })
    List<TPermit> getAllMenuPermits(Integer permitId);


    // 获得所有的权限/菜单列表（列表）
    @Select("SELECT * FROM t_permit WHERE flag = 0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "status", property = "status"),
            @Result(column = "permit_name", property = "permitName"),
            @Result(column = "permit_path", property = "permitPath"),
            @Result(column = "permit_icon", property = "permitIcon"),
            @Result(column = "permit_desc", property = "permitDesc"),
            @Result(column = "parent_id", property = "parentId")
    })
    List<TPermit> getAllPermitList();
}
