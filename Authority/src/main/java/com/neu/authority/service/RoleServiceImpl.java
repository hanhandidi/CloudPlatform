package com.neu.authority.service;

import com.neu.authority.dao.PermitDao;
import com.neu.authority.dao.RoleDao;
import com.neu.authority.entity.Message;
import com.neu.authority.entity.TPermit;
import com.neu.authority.entity.TRolePermit;
import com.neu.authority.entity.TUserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;
    @Resource
    PermitDao permitDao;


    @Override
    public Message insertRole(TUserRole role) {
        if (roleDao.selectRoleByName(role) != null)
            return new Message(201, "当前工厂已存在该角色", null);
        if (roleDao.insertRole(role) == 1) { // 插入角色
            // 组装该角色所有待插入的权限
            List<TRolePermit> rolePermits = new ArrayList<>();
            List<TPermit> permits = permitDao.getAllPermitList();
            for (TPermit permit : permits) {
                rolePermits.add(new TRolePermit(1,
                        role.getCreateUserid(),
                        role.getUpdateUserid(),
                        role.getFactoryId(),
                        role.getId(),
                        permit.getId()));
            }
            // 将组装的所有无效权限插入角色权限表中
            roleDao.insertRolePermits(rolePermits);
            return new Message(200, "新建角色成功", role);
        }
        return new Message(202, "新建角色失败 ", null);
    }

    @Override
    public Message updateRole(TUserRole role) {
        TUserRole roleInDB = roleDao.selectRoleByName(role);
        if (roleInDB != null && roleInDB.getId() != role.getId())
            return new Message(201, "当前工厂已存在该角色", null);
        roleDao.updateRole(role);
        return new Message(200, "修改角色成功", null);
    }

    @Override
    public Message deleteRole(TUserRole role) {
        roleDao.deleteRole(role);
        return new Message(200, "删除角色成功", null);
    }

    @Override
    public TUserRole selectRoleById(TUserRole role) {
        return roleDao.selectRoleById(role);
    }

    @Override
    public List<TUserRole> selectRolesByFactoryId(TUserRole role) {
        return roleDao.selectRolesByFactoryId(role);
    }

    @Override
    public Message insertRolePermit(TRolePermit rolePermit) {
        if (roleDao.selectRoleWithPermit(rolePermit) != null)
            return new Message(201, "该角色已存在该权限", null);
        roleDao.insertRolePermit(rolePermit);
        return new Message(200, "新建角色权限成功", null);
    }

    @Override
    public Message deleteRolePermit(TRolePermit rolePermit) {
        roleDao.deleteRolePermit(rolePermit);
        return new Message(200, "删除角色权限成功", null);
    }

    @Override
    public List<TRolePermit> selectPermitsByRoleId(TRolePermit rolePermit) {
        return roleDao.selectPermitsByRoleId(rolePermit);
    }

    @Override
    public List<TRolePermit> selectPermitsInUserByRoleId(TRolePermit rolePermit) {
        return roleDao.selectPermitsInUserByRoleId(rolePermit);
    }

    @Override
    public List<String> selectPermitIdsByRoleId(TRolePermit rolePermit) {
        return roleDao.selectPermitIdsByRoleId(rolePermit);
    }

    @Override
    public Message updateRolePermits(TRolePermit rolePermit) {
        // 重置该角色下的所有权限（删除，flag=1）
        roleDao.deleteRolePermits(rolePermit);
        // 批量将选中权限改为可用（创建，flag=0）
        // 开始组装
        List<TRolePermit> rolePermits = new ArrayList<>();
        List<Long> permitIds = rolePermit.getPermitIds();
        if (permitIds.size() != 0) {
            for (Long permitId : permitIds) {
                rolePermits.add(new TRolePermit(rolePermit.getUpdateUserid(),
                        rolePermit.getFactoryId(),
                        rolePermit.getRoleId(),
                        permitId));
            }
            roleDao.updateRolePermits(rolePermits);
        }
        return new Message(200, "修改角色权限成功", null);
    }
}
