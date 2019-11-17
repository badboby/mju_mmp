package com.mju.mmpo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.mju.mmpo.dao.ISysLogDao;
import com.mju.mmpo.domain.SysLog;
import com.mju.mmpo.service.ISysLogService;
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
public class ISysLogServiceImpl implements ISysLogService {
    @Resource
    private ISysLogDao sysLogDao;
    @Override
    public List<SysLog> findAll(int page,int size) throws Exception{
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }
}
