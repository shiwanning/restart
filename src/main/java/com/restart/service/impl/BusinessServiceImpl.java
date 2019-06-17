package com.restart.service.impl;

import com.restart.bean.Business;
import com.restart.dao.BusinessDao;
import com.restart.dto.BusinessDto;
import com.restart.service.BusinessService;
import com.restart.util.CommonUtil;
import com.restart.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Autowired
    private BusinessDao businessDao;

    @Value("${businessImage.savePath}")
    private String savePath;

    @Value("${businessImage.url}")
    private String filePath;

    @Override
    public boolean insertBusiness(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);
        try {
            if(CommonUtil.isMultipartFileExist(businessDto.getMultipartFile())){
                String fileName = FileUtil.saveFile(businessDto.getMultipartFile(), savePath);
                business.setImgFileName(fileName);
                business.setCommentTotalNum(0L);
                business.setNumber(0);
                business.setStarTotalNum(0L);
            }else {
                return  false;
            }
            businessDao.insertEx(business);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return  false;
        }
        return true;
    }



}
