package com.restart.dao;

import com.restart.bean.Ad;

import java.util.List;

public interface AdDao   {
    List<Ad> getAdListByPage(Ad ad);

    boolean  addAd(Ad ad);
}
