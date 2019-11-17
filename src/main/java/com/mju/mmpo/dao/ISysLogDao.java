package com.mju.mmpo.dao;

import com.mju.mmpo.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface ISysLogDao {
    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;
    @Select("select * from sysLog")
    List<SysLog> findAll() throws Exception;
}
