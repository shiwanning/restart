package com.restart.dao;

import com.restart.bean.Business;

import java.util.List;

public interface BusinessDao {

    int insertEx(Business business);

    List<Business> selectBusinessByPage(Business business);

    Business selectById(Long id);

    List<Business> staticsBusinessNumber();
}
