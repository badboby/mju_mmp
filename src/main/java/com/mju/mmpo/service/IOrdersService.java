package com.mju.mmpo.service;

import com.mju.mmpo.domain.Orders;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IOrdersService {
    List<Orders> findAll(int page,int size) throws Exception;
    void save(Orders orders) throws Exception;
    Orders selectById(String id) throws Exception;
    void delete(String id) throws Exception;
}
