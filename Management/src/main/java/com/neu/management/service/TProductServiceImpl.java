package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.TProductMapper;
import com.neu.management.model.TProduct;
import com.neu.management.util.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TProductServiceImpl implements TProductService {
    @Autowired
    private TProductMapper tProductMapper;
    @Override
    public PageInfo<TProduct> selectProducts(TProduct record,Long currentPage) {
        if(currentPage==null)
            currentPage=1;
        PageHelper.startPage(currentPage, Define.PAGE_SIZE);
        PageInfo<TProduct> pageInfo = new PageInfo<>(tProductMapper.selectProducts(record));
        return pageInfo;
    }

    @Override
    public int addProduct(TProduct tProduct) {
        if(tProduct==null||tProduct.getProductNum()==null)
            return -1;
        if(selectByNum(tProduct.getProductNum())!=null)
            return -2;
        tProduct.setId(null);
        Timestamp t=new Timestamp(System.currentTimeMillis());
        tProduct.setCreateTime(t);
        tProduct.setUpdateTime(t);
        return tProductMapper.addProduct(tProduct);
    }

    @Override
    public int updateProduct(TProduct tProduct) {
//        tProduct.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if (tProduct==null||tProduct.getId()==null||tProduct.getProductName()==null)
            return -1;
        TProduct tp =selectByName(tProduct.getProductName());
        if(tp!=null&&tp.getId()!=tProduct.getId())
            return -2;
        Timestamp t=new Timestamp(System.currentTimeMillis());
        tProduct.setUpdateTime(t);
        return tProductMapper.updateProduct(tProduct);
    }

    @Override
    public int deleteProductByIds(List<Long> ids) {
        if(ids==null)
            return-1;
        Map<String,List<Long>> map= new HashMap<String, List<Long>>();
        map.put("list",ids);
        return tProductMapper.deleteProductsByIds(map);
    }

    @Override
    public int deleteById(Long id) {
        if(id==null)
            return-1;
        return tProductMapper.deleteById(id);
    }

    @Override
    public TProduct selectById(Long id) {
        if(id==null)
            return null;
        return tProductMapper.selectById(id);
    }
    @Override
    public TProduct selectByNum(String num) {
        if(num==null)
            return null;
        return tProductMapper.selectByNum(num);
    }
    @Override
    public TProduct selectByName(String name) {
        if(name==null)
            return null;
        return tProductMapper.selectByName(name);
    }
}
