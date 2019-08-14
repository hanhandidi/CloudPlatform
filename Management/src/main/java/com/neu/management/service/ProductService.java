package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProduct;

import java.util.List;

public interface ProductService {
    // 可根据产品的名称、序列号查询出对应工厂所有产品、并分页
    PageInfo<TProduct> selectProducts(TProduct tProduct, Integer currentPage);
    // 根据条件获取所有的产品信息
    List<TProduct> selectProducts(TProduct tProduct);
    // 添加产品
    TProduct addProduct(TProduct tProduct);

    TProduct updateProduct(TProduct tProduct);

    // 批量删除
    int deleteProductByIds(List<Integer> ids);

    int deleteById(Integer id);
    // 根据ID获取
    TProduct selectById(Integer id);
    // 根据产品序列号获取
    TProduct selectByNum(String num);
    // 根据产品名称获取
    TProduct selectByName(String name);
}
