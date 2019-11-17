package com.mju.mmpo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.mju.mmpo.dao.IOrdersDao;
import com.mju.mmpo.domain.Orders;
import com.mju.mmpo.service.IOrdersService;
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
public class OrdersServiceImpl implements IOrdersService {
    @Resource
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //pageHelper一定需要写在需要分页的方法的第一行
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);

        return ordersDao.findAll();
    }

    @Override
    public void save(Orders orders) throws Exception{
        ordersDao.save(orders);
    }

    @Override
    public Orders selectById(String id) throws Exception{
        return ordersDao.selectById(id);
    }

    @Override
    public void delete(String id) throws Exception{
        ordersDao.delete(id);
    }
}
