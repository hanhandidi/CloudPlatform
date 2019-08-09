package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProduct;

import java.security.PublicKey;
import java.util.List;


public interface TProductService {
    public PageInfo<TProduct> selectProducts(TProduct record, Integer currentPage);
    public int addProduct(TProduct tProduct);
    public int updateProduct(TProduct tProduct);
    public int deleteProductByIds(List<Integer> ids);
    public int deleteById(Integer id);
    public TProduct selectById(Integer id);
    public TProduct selectByNum(String num);
}
