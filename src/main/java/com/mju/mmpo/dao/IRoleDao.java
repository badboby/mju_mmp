package com.mju.mmpo.dao;

import com.mju.mmpo.domain.Permission;
import com.mju.mmpo.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IRoleDao {
    //根据用户的id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(property = "id",id = true,column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.mju.mmpo.dao.IPermissionDao.selectPermissionByRoleId"))
    })
    public List<Role> selectRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    public List<Role> findAll () throws Exception;

    @Select("select * from role where id=#{id}")
    public Role selectById(String id) throws Exception;

    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;

    @Delete("delete from role where id=#{id}")
    public void deleteById(String id) throws Exception;

    @Select("select * from permission where id not in (select * from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId) throws Exception;

    @Insert("insert into role_permission (roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId) throws Exception;

    @Delete("delete from users_role where roleId = #{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId) throws Exception;

    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId) throws Exception;
}
