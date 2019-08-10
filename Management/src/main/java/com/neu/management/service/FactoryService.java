package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TFactory;

import java.util.List;

public interface FactoryService {

    TFactory addFactory(TFactory tFactory);

    void deleteFactory(Integer id);

    TFactory updateFactory(TFactory tFactory);

    TFactory getFactory(Integer id);

    List<TFactory> getAllFactory();

    PageInfo<TFactory> listFactory(Integer currPage, TFactory tFactory);
}
