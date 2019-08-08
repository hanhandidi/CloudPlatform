package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProduct;

import java.security.PublicKey;


public interface TProductService {
    public PageInfo<TProduct> selectProducts(TProduct record, Integer currentPage);
    public int addProduct(TProduct tProduct);
    public int updateProduct(TProduct tProduct);
    public int deleteProductById(Integer[] id);
}
