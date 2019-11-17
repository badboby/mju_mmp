package com.mju.mmpo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.mju.mmpo.dao.IUserDao;
import com.mju.mmpo.domain.Role;
import com.mju.mmpo.domain.UserInfo;
import com.mju.mmpo.service.IUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Allen李
 * @date
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(UserInfo userInfo){
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    public List<UserInfo> findAll(int page,int size) throws Exception{
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public UserInfo selectById(String id) throws Exception {
        return userDao.selectById(id);
    }

    @Override
    public void delete(String id) throws Exception{
        //这边不单单只是删除该用户还应该删除该用户在关联表中的值
        userDao.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.selectByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),bCryptPasswordEncoder.encode(userInfo.getPassword()), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception{
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String usersId, String[] roleIds) throws Exception {
        for (String roleId:roleIds){
            userDao.addRoleToUser(usersId,roleId);
        }
    }
}
