package com.restart.service;


import com.restart.dto.AdDto;
import com.restart.dto.PageResult;


public interface AdService {
    PageResult<AdDto> getAdList(AdDto adDto);

    boolean addAd(AdDto adDto);

    boolean deleteById(Long adDto);

    boolean update(AdDto adDto);
}
