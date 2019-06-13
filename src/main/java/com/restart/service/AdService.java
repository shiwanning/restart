package com.restart.service;


import com.restart.Exception.BaseException;
import com.restart.dto.AdDto;
import com.restart.dto.PageResult;


public interface AdService {
    PageResult<AdDto> getAdList(AdDto adDto);

    boolean addAd(AdDto adDto) throws BaseException;
}
