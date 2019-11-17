package com.mju.mmpo.dao;

import com.mju.mmpo.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
        public List<Permission> selectPermissionByRoleId (String roleId) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission (permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission) throws Exception;

    @Select("select * from permission where id=#{id}")
    public Permission selectById(String id) throws Exception;

    @Delete("delete from permission where id=#{id}")
    public void deleteById(String id);

    @Delete("delete from role_permission where permissionId = #{permissionId}")
    void deleteFromRole_Permission(String permissionId) throws Exception;
}
