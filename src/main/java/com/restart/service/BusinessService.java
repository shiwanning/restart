package com.restart.service;

import com.restart.dto.BusinessDto;

import java.util.List;

public interface BusinessService {

    boolean insertBusiness(BusinessDto businessDto);


    List<BusinessDto> getBusinessByPage(BusinessDto businessDto);

    BusinessDto selectById(Long id);
}
