package com.mju.mmpo.dao;

import com.mju.mmpo.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface ITravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    List<Traveller> selectByOrdersId(String ordersId) throws Exception;
    @Select("select * from traveller")
    public List<Traveller> findAll();
}
