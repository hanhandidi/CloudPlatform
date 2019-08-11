package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProduct;

import java.security.PublicKey;
import java.util.List;


public interface ProductService {
    PageInfo<TProduct> selectProducts(TProduct record, Integer currentPage);

    int addProduct(TProduct tProduct);

    int updateProduct(TProduct tProduct);

    int deleteProductByIds(List<Integer> ids);

    int deleteById(Integer id);

    TProduct selectById(Integer id);

    TProduct selectByNum(String num);

    TProduct selectByName(String name);
}
