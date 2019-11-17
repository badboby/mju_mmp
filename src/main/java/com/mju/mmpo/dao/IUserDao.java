package com.mju.mmpo.dao;

import com.mju.mmpo.domain.Role;
import com.mju.mmpo.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IUserDao {
    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.mju.mmpo.dao.IRoleDao.selectRoleByUserId"))
    })
    public UserInfo selectByUsername(String username) throws Exception;
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.mju.mmpo.dao.IRoleDao.selectRoleByUserId"))
    })
    public UserInfo selectById(String id) throws Exception;

    @Insert("insert into users (email,username,password,phoneNum,status) " +
            "values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Delete("delete from users where id=#{id}")
    public void delete(String id) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles (String userId) throws Exception;

    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId) throws Exception;
}
