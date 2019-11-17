package com.mju.mmpo.dao;

import com.mju.mmpo.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IOrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.mju.mmpo.dao.IProductDao.selectById")),
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.mju.mmpo.dao.IProductDao.selectById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.mju.mmpo.dao.ITravellerDao.selectByOrdersId")),
            @Result(column = "memberId",property = "member",one = @One(select = "com.mju.mmpo.dao.IMemberDao.selectById"))
    })
    Orders selectById(String id) throws Exception;

    @Insert("insert into orders(orderNum,orderTime,peopleCount,orderDesc,payType,orderStatus,productId,memberId) " +
            "values(#{orderNum},#{orderTime},#{peopleCount},#{orderDesc},#{payType},#{orderStatus},#{productId},#{memberId})")
    void save(Orders orders) throws Exception;

    @Delete("delete from orders where id=#{id}")
    void delete(String id) throws  Exception;
}
