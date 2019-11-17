package com.mju.mmpo.service;

import com.mju.mmpo.domain.SysLog;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface ISysLogService {

    List<SysLog> findAll(int page,int size) throws Exception;

    void save(SysLog sysLog)throws Exception;
}
