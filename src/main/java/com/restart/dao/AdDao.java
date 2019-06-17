package com.restart.dao;

import com.restart.bean.Ad;

import java.util.List;

public interface AdDao   {
    List<Ad> getAdListByPage(Ad ad);

    boolean  addAd(Ad ad);

    Long  deleteById(Long id);

    Ad selectById(Long id);

    Long updateAd(Ad ad);
}
