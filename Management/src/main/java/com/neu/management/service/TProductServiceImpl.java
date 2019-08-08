package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.TProductMapper;
import com.neu.management.model.TProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class TProductServiceImpl implements TProductService {
    @Autowired
    private TProductMapper tProductMapper;
    @Override
    public PageInfo<TProduct> selectProducts(TProduct record,Integer currentPage) {
        if(currentPage==null)
            currentPage=1;
        PageHelper.startPage(currentPage,2);
        PageInfo<TProduct> pageInfo = new PageInfo<>(tProductMapper.selectProducts(record));
        return pageInfo;
    }

    @Override
    public int addProduct(TProduct tProduct) {
        if(tProduct.getProductNum()==null)
            return -1;
        tProduct.setId(null);
        return tProductMapper.addProduct(tProduct);
    }

    @Override
    public int updateProduct(TProduct tProduct) {
//        tProduct.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return tProductMapper.updateProduct(tProduct);
    }

    @Override
    public int deleteProductById(Integer[] id) {
        return tProductMapper.deleteProductsById(id);
    }

}
