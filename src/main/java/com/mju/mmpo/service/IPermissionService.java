package com.mju.mmpo.service;

import com.mju.mmpo.domain.Permission;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IPermissionService {
    List<Permission> findAll(int page,int size) throws Exception;
    void save(Permission permission) throws Exception;
    Permission selectById(String id) throws Exception;
    void deleteByPermissionId(String id) throws Exception;
}
