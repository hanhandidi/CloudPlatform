package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.ProductDao;
import com.neu.management.model.TProduct;
import com.neu.management.util.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public PageInfo<TProduct> selectProducts(TProduct tProduct, Integer currentPage) {
        if ( currentPage == 0 ) currentPage = 1;
        PageHelper.startPage(currentPage, Define.PAGE_SIZE);
        return new PageInfo<>(productDao.selectProducts(tProduct));
    }

    @Override
    public TProduct addProduct(TProduct tProduct) {
        // 同一工厂产品不可重名
        // 同一工厂产品序列号不可重复
        if(productDao.selectByNameAndFactoryId(tProduct) != null || productDao.selectByNumAndFactoryId(tProduct) != null){
            return null;
        } else
        {
            productDao.addProduct(tProduct);
            return tProduct;
        }
    }

    @Override
    @CachePut(value="TProduct",key="T(String).valueOf('TProduct').concat('-').concat(#tProduct.id)",unless="#result == null")
    public TProduct updateProduct(TProduct tProduct) {
        // 同一工厂产品不可重名
        // 同一工厂产品序列号不可重复
        if(productDao.selectByNameAndFactoryId(tProduct) != null || productDao.selectByNumAndFactoryId(tProduct) != null){
            return null;
        } else
        {
            productDao.updateProduct(tProduct);
            return tProduct;
        }
    }

    @Override
    public int deleteProductByIds(List<Integer> ids) {
        if ( ids == null ) return -1;
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("list", ids);
        return productDao.deleteProductsByIds(map);
    }

    @Override
    @CacheEvict(value="TProduct",key="T(String).valueOf('TProduct').concat('-').concat(#id)")
    public int deleteById(Integer id) {
        if ( id == null ) return -1;
        if ( productDao.isInGetOrder(id) == null ){
            productDao.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    @Cacheable(value="TProduct",key="T(String).valueOf('TProduct').concat('-').concat(#id)",unless="#result == null")
    public TProduct selectById(Integer id) {
        if ( id == null ) return null;
        return productDao.selectById(id);
    }

    @Override
    public TProduct selectByNum(String num) {
        if ( num == null ) return null;
        return productDao.selectByNum(num);
    }

    @Override
    public TProduct selectByName(String name) {
        if ( name == null ) return null;
        return productDao.selectByName(name);
    }
}
