package com.neu.authority.service;

import com.neu.authority.entity.*;

import java.util.List;

public interface RoleService {

    // 新增一个角色（当前工厂）（将角色赋给用户在UserDao）
    Message insertRole(TUserRole role);

    // 修改一个角色（当前工厂）
    Message updateRole(TUserRole role);

    // 删除一个角色（当前工厂）, flag置1
    Message deleteRole(TUserRole role);

    // 获取一个角色（当前工厂）
    TUserRole selectRoleById(TUserRole role);

    // 查询所有的角色（当前工厂）
    List<TUserRole> selectRolesByFactoryId(TUserRole role);

    // 新增角色的一个权限/菜单
    Message insertRolePermit(TRolePermit rolePermit);

    // 删除角色的一个权限/菜单
    Message deleteRolePermit(TRolePermit rolePermit);

    // 查询角色的所有权限/菜单（子）; t_role_permit(roleId, permitId); t_permit(permitId, parentId)
    List<TRolePermit> selectPermitsByRoleId(TRolePermit rolePermit);

    // 查询用户角色的权限/菜单的变动情况
    List<TRolePermit> selectPermitsInUserByRoleId(TRolePermit rolePermit);

    // 查询用户所有权限的IDs
    List<String> selectPermitIdsByRoleId(TRolePermit rolePermit);

    // 批量修改角色的权限
    Message updateRolePermits(TRolePermit rolePermit);
}
