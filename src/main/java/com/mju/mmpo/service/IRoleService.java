package com.mju.mmpo.service;

import com.mju.mmpo.domain.Permission;
import com.mju.mmpo.domain.Role;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IRoleService {
    List<Role> findAll(int page,int size) throws Exception;
    void save(Role role) throws Exception;
    Role findById(String id) throws Exception;
    List<Permission> findOtherPermission(String roleId) throws Exception;
    void addPermissionToRole(String roleId,String[] permissionId) throws Exception;
    void deleteRoleById(String roleId) throws Exception;
}
