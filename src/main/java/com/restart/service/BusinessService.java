package com.restart.service;

import com.restart.dto.BusinessDto;
import com.restart.dto.PageResult;


public interface BusinessService {

    boolean insertBusiness(BusinessDto businessDto);


    PageResult<BusinessDto> getBusinessByPage(BusinessDto businessDto);

    BusinessDto selectById(Long id);
}
