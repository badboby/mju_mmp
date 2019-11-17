package com.mju.mmpo.service;


import com.mju.mmpo.domain.Product;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IProductService {

    public List<Product> findAll(int page,int size) throws Exception;

    public void save(Product product) throws Exception;

    public void delete(String id) throws Exception;
}
