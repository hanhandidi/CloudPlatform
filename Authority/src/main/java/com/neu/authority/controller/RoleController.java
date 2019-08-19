package com.neu.authority.controller;

import com.neu.authority.entity.Message;
import com.neu.authority.entity.TRolePermit;
import com.neu.authority.entity.TUserRole;
import com.neu.authority.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    // 添加角色
    // createUserid, updateUserid, roleDesc, roleName, factoryId
    @PostMapping(value = "")
    @ResponseBody
    public Message insertRole(TUserRole role) {
        return roleService.insertRole(role);
    }

    // 修改角色
    // updateUserid, roleDesc, roleName, roleStatus, factoryId, id
    @PutMapping(value = "")
    @ResponseBody
    public Message updateRole(TUserRole role) {
        return roleService.updateRole(role);
    }

    // 删除角色
    // updateUserid, factoryId, id
    @DeleteMapping(value = "")
    @ResponseBody
    public Message deleteRole(TUserRole role) {
        return roleService.deleteRole(role);
    }

    // 获取角色
    // factoryId, id
    @GetMapping(value = "")
    @ResponseBody
    public Message selectRole(TUserRole role) {
        return new Message(200, "获取角色成功", roleService.selectRoleById(role));
    }

    // 角色列表
    // factoryId
    @RequestMapping(value = "/list")
    @ResponseBody
    public Message selectAllRolesByFactoryId(TUserRole role) {
        return new Message(200, "获取所有角色成功", roleService.selectRolesByFactoryId(role));
    }

    // 为角色添加一条权限
    // createUserid, updateUserid, factoryId, roleId, permitId
    @PostMapping(value = "/permit")
    @ResponseBody
    public Message insertRolePermit(TRolePermit rolePermit) {
        return roleService.insertRolePermit(rolePermit);
    }

    // 删除角色的一条权限
    // updateUserid, factoryId, id
    @DeleteMapping(value = "/permit")
    @ResponseBody
    public Message deleteRolePermit(TRolePermit rolePermit) {
        return roleService.deleteRolePermit(rolePermit);
    }

    // 角色的权限列表
    // roleId, factoryId
    @RequestMapping(value = "/permit/list")
    @ResponseBody
    public Message selectPermitsByRoleId(TRolePermit rolePermit) {
        return new Message(200, "获取角色所有权限成功", roleService.selectPermitsByRoleId(rolePermit));
    }

    // roleId, factoryId
//    @RequestMapping(value = "/permit/getAll")
//    @ResponseBody
//    public Message selectPermitsInUserByRoleId(TRolePermit rolePermit) {
//        return new Message(200, "获取所有角色成功", roleService.selectPermitsInUserByRoleId(rolePermit));
//    }


    // -------------------------------

    // roleId, factoryId
    // 角色的权限ID列表
    @RequestMapping(value = "/permit/id/list")
    @ResponseBody
    public Message selectPermitIdsByRoleId(TRolePermit rolePermit) {
        return new Message(200, "获取角色所有权限ID成功", roleService.selectPermitIdsByRoleId(rolePermit));
    }

    // 批量修改 用户多条权限
    // createUserid, updateUserid, factoryId, roleId, permitId
    @PostMapping(value = "/permits")
    @ResponseBody
    public Message insertRolePermits(@RequestBody TRolePermit rolePermit) {

        return roleService.updateRolePermits(rolePermit);
    }
}
