package com.restart.service.impl;

import com.restart.bean.Business;
import com.restart.dao.BusinessDao;
import com.restart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {


    @Autowired
    private BusinessDao businessDao;

    @Override
    public List<Business> staticsBusinessNumber() {
        List<Business> businesses = businessDao.staticsBusinessNumber();

        for(Business bo : businesses){

        }
        return  null;
    }
}
