package com.neu.authority.service;

import com.neu.authority.dao.PermitDao;
import com.neu.authority.entity.TPermit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermitServiceImpl implements PermitService {

    @Resource
    PermitDao permitDao;


    @Override
    public List<TPermit> getAllPermits() {
        return permitDao.getAllMenuPermits(0);
    }

    @Override
    public List<TPermit> getAllPermitList() {
        return permitDao.getAllPermitList();
    }
}
