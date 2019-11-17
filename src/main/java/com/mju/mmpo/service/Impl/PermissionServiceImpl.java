package com.mju.mmpo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.mju.mmpo.dao.IPermissionDao;
import com.mju.mmpo.domain.Permission;
import com.mju.mmpo.service.IPermissionService;
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
public class PermissionServiceImpl implements IPermissionService{
    @Resource
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission selectById(String id) throws Exception {
        return permissionDao.selectById(id);
    }

    @Override
    public void deleteByPermissionId(String id) throws Exception {
        //删除该权限的同时应当先把关联表中的权限id删除
        permissionDao.deleteFromRole_Permission(id);
        permissionDao.deleteById(id);
    }


}
