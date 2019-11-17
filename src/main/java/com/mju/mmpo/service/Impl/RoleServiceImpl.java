package com.mju.mmpo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.mju.mmpo.dao.IRoleDao;
import com.mju.mmpo.domain.Permission;
import com.mju.mmpo.domain.Role;
import com.mju.mmpo.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Allen李
 * @date
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Resource
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.selectById(id);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public void deleteRoleById(String roleId) throws Exception {
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        roleDao.deleteById(roleId);
    }

}
