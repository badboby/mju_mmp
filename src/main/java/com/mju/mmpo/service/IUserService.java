package com.mju.mmpo.service;

import com.mju.mmpo.domain.Role;
import com.mju.mmpo.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(int page,int size) throws Exception;
    UserInfo selectById(String id) throws Exception;
    void save(UserInfo userInfo) throws Exception;
    void delete(String id) throws Exception;
    List<Role> findOtherRoles(String uerId) throws Exception;
    void addRoleToUser(String usersId,String[] roleIds) throws Exception;
}
