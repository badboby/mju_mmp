package com.mju.mmpo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.mju.mmpo.dao.IProductDao;
import com.mju.mmpo.domain.Product;
import com.mju.mmpo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
class IProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(int page,int size) throws Exception{
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.insertP(product);
    }

    @Override
    public void delete(String id) throws Exception {
        productDao.deleteP(id);
    }


}

