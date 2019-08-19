package com.neu.authority.dao;

import com.neu.authority.entity.TPermit;
import com.neu.authority.entity.TRolePermit;
import com.neu.authority.entity.TUserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.management.relation.RoleList;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Component
public interface RoleDao {

    // user(roleId) -> role == rolepermit(roleId, permitId) == permit

    // 新增一个角色（当前工厂）（将角色赋给用户在UserDao）
    @Insert("INSERT t_user_role (create_userid, update_userid, role_desc, role_name, factory_id)" +
            " VALUES (#{createUserid}, #{updateUserid}, #{roleDesc}, #{roleName}, #{factoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertRole(TUserRole role);


    // 通过角色名查询一个角色（id、有效）（供一对一查询，非传入user）
    @Select("SELECT * FROM t_user_role WHERE id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_userid", property = "createUserid"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_userid", property = "updateUserid"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_status", property = "roleStatus"),
            @Result(column = "factory_id", property = "factoryId")
    })
    TUserRole selectRoleByIdInDB(long id);


    // 通过角色名查询一个角色（id、有效）
    @Select("SELECT * FROM t_user_role WHERE id = #{id} AND flag = 0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_userid", property = "createUserid"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_userid", property = "updateUserid"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_status", property = "roleStatus"),
            @Result(column = "factory_id", property = "factoryId")
    })
    TUserRole selectRoleById(TUserRole role);

    // 通过角色名查询一个角色（角色名、当前工厂、有效）
    @Select("SELECT * FROM t_user_role WHERE role_name = #{roleName} AND factory_id = #{factoryId} AND flag = 0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_userid", property = "createUserid"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_userid", property = "updateUserid"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_status", property = "roleStatus"),
            @Result(column = "factory_id", property = "factoryId")
    })
    TUserRole selectRoleByName(TUserRole role);

    // 修改一个角色（当前工厂）
    @Update("UPDATE t_user_role" +
            " SET update_userid = #{updateUserid}, role_desc = #{roleDesc}" +
            ", role_name = #{roleName}, role_status = #{roleStatus}" +
            " WHERE id = #{id} AND factory_id = #{factoryId}")
    void updateRole(TUserRole role);

    // 删除一个角色（当前工厂）, flag置1
    @Update("UPDATE t_user_role" +
            " SET update_userid = #{updateUserid}, flag = 1" +
            " WHERE id = #{id} AND factory_id = #{factoryId}")
    void deleteRole(TUserRole role);

    // 查询所有的角色（当前工厂）
    @Select("SELECT * FROM t_user_role WHERE factory_id = #{factoryId} and flag = 0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_userid", property = "createUserid"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_userid", property = "updateUserid"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_status", property = "roleStatus"),
            @Result(column = "factory_id", property = "factoryId")
    })
    List<TUserRole> selectRolesByFactoryId(TUserRole role);

    //-------------------

    // 新增角色的一个权限/菜单（当前工厂）
    @Insert("INSERT t_role_permit (create_userid, update_userid, factory_id, role_id, permit_id)" +
            " VALUES (#{createUserid}, #{updateUserid}, #{factoryId}, #{roleId}, #{permitId})")
    void insertRolePermit(TRolePermit rolePermit);

    // 查询该角色是否有某权限（roleId，permitId，有效，当前工厂）
    @Select("SELECT * FROM t_role_permit" +
            " WHERE role_id = #{roleId} AND permit_id = #{permitId} AND factory_id = #{factoryId} AND flag = 0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "createTime", property = "create_time"),
            @Result(column = "createUserid", property = "create_userid"),
            @Result(column = "updateTime", property = "update_time"),
            @Result(column = "updateUserid", property = "update_userid"),
            @Result(column = "factoryId", property = "factory_id"),
            @Result(column = "roleId", property = "role_id"),
            @Result(column = "permitId", property = "permit_id")
    })
    TRolePermit selectRoleWithPermit(TRolePermit rolePermit);


    // 修改角色的一个权限/菜单
//    void updateRolePermit(TRolePermit rolePermit);

    // 删除角色的一个权限/菜单（当前工厂）
    @Update("UPDATE t_role_permit" +
            " SET update_userid = #{updateUserid}, flag = 1" +
            " WHERE id = #{id} AND factory_id = #{factoryId}")
    void deleteRolePermit(TRolePermit rolePermit);

    // 查询角色的所有权限/菜单（子）; t_role_permit(roleId, permitId); t_permit(permitId, parentId)（当前工厂）
    @Select("SELECT * FROM t_role_permit" +
            " WHERE role_id = #{roleId} AND factory_id = #{factoryId} AND flag = 0")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_userid", property = "createUserid"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_userid", property = "updateUserid"),
            @Result(column = "factory_id", property = "factoryId"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "permit_id", property = "permitId"),
            @Result(column = "permit_id", property = "permit",
                    javaType = TPermit.class, one = @One(select = "com.neu.authority.dao.PermitDao.selectPermitById"))
    })
    List<TRolePermit> selectPermitsByRoleId(TRolePermit rolePermit);

    // 查询用户角色的权限/菜单的变动情况
    @Select("SELECT * FROM t_role_permit" +
            " WHERE role_id = #{roleId} AND factory_id = #{factoryId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_userid", property = "createUserid"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_userid", property = "updateUserid"),
            @Result(column = "factory_id", property = "factoryId"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "permit_id", property = "permitId"),
            @Result(column = "permit_id", property = "permit",
                    javaType = TPermit.class, one = @One(select = "com.neu.authority.dao.PermitDao.selectPermitById"))
    })
    List<TRolePermit> selectPermitsInUserByRoleId(TRolePermit rolePermit);

    // --------------------
    // 查询角色所有权限的IDS
    @Select("SELECT permit_id FROM t_role_permit" +
            " WHERE role_id = #{roleId} AND factory_id = #{factoryId} AND flag = 0")
    List<String> selectPermitIdsByRoleId(TRolePermit rolePermit);

    // 为角色添加多条权限 (批量添加)
    @InsertProvider(type = Provider.class, method = "insertBatch")
    void insertRolePermits(List<TRolePermit> rolePermits);

    // 修改角色所有权限为无效（删除）
    @Update("UPDATE t_role_permit SET flag = 1 WHERE role_id = #{roleId}")
    void deleteRolePermits(TRolePermit rolePermit);

    // 修改角色所有权限（批量修改）

    @Update({"<script>" +
            "<foreach collection=\"rolePermits\" item=\"item\" separator=\";\">" +
            " UPDATE t_role_permit" +
            " SET flag = 0," +
            " update_userid = #{item.updateUserid}" +
            " WHERE permit_id = #{item.permitId}" +
            " AND role_id = #{item.roleId}" +
            "</foreach>" +
            "</script>"})
    void updateRolePermits(@Param("rolePermits") List<TRolePermit> rolePermits);

    class Provider {
        /* 批量插入 */
        public String insertBatch(Map map) {
            List<TRolePermit> rolePermits = (List<TRolePermit>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT t_role_permit (create_userid, update_userid, factory_id, role_id, permit_id, flag)" +
                    " VALUES ");
//            " VALUES (#{createUserid}, #{updateUserid}, #{factoryId}, #{roleId}, #{permitId})")
            MessageFormat mf = new MessageFormat(
                    "(#'{'list[{0}].createUserid}, #'{'list[{0}].updateUserid}, #'{'list[{0}].factoryId}, " +
                            "#'{'list[{0}].roleId}, #'{'list[{0}].permitId}, #'{'list[{0}].flag})"
            );
            for (int i = 0; i < rolePermits.size(); i++) {
                sb.append(mf.format(new Object[] {i}));
                if (i < rolePermits.size() - 1)
                    sb.append(",");
            }
            return sb.toString();
        }
    }

}
