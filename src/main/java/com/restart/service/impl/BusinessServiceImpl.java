package com.restart.service.impl;

import com.restart.bean.Business;
import com.restart.constant.CategoryConst;
import com.restart.dao.BusinessDao;
import com.restart.dto.BusinessDto;
import com.restart.dto.PageResult;
import com.restart.service.BusinessService;
import com.restart.util.CommonUtil;
import com.restart.util.FileUtil;
import org.jasig.cas.client.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


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


    @Override
    public PageResult<BusinessDto> getBusinessByPage(BusinessDto businessDto) {

        LinkedList<BusinessDto> result = new LinkedList<>();
        Business condition = new Business();
        BeanUtils.copyProperties(businessDto, condition);

        String keyWord = businessDto.getKeyWord();

        //TODO 全文检索
        if(CommonUtils.isNotEmpty(keyWord)){
            condition.setTitle(keyWord);
            condition.setSubtitle(keyWord);
            condition.setDesc(keyWord);
        }

        //类别为All
        if(CommonUtils.isNotEmpty(businessDto.getCategory()) && CategoryConst.ALL.equalsIgnoreCase(businessDto.getCategory())){
           condition.setCategory(null);
        }
        List<Business> businesses = businessDao.selectBusinessByPage(condition);

        for(Business cr: businesses){
            BusinessDto bs = new BusinessDto();
            BeanUtils.copyProperties(cr, bs);

            Long star = generateStar(cr);
            bs.setStar(star.intValue());
            bs.setImgFileName(filePath + cr.getImgFileName());

            result.add(bs);
        }


        return new PageResult<BusinessDto>(result, condition.getPage());
    }

    private Long generateStar(Business business){
        
        if(business.getStarTotalNum()!=null && business.getCommentTotalNum()!=null && business.getCommentTotalNum() != 0){
               return  business.getCommentTotalNum() / business.getStarTotalNum(); 
        }
        return 0L;
    }


    @Override
    public BusinessDto selectById(Long id) {
        return null;
    }
}
