package com.mju.mmpo.dao;


import com.mju.mmpo.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IProductDao {
    //查询所有的产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Select("select * from product where id=#{id}")
    public  Product selectById(String id);

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void insertP(Product product);

    @Delete("delete from product where id=#{id}")
    void deleteP(String id);
}
