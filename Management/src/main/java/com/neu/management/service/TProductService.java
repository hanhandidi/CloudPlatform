package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProduct;

import java.security.PublicKey;
import java.util.List;


public interface TProductService {
    public PageInfo<TProduct> selectProducts(TProduct record, Long currentPage);
    public int addProduct(TProduct tProduct);
    public int updateProduct(TProduct tProduct);
    public int deleteProductByIds(List<Long> ids);
    public int deleteById(Long id);
    public TProduct selectById(Long id);
    public TProduct selectByNum(String num);
    public TProduct selectByName(String name);
}
