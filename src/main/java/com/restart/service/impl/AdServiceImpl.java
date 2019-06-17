package com.restart.service.impl;

import com.restart.Exception.BaseException;
import com.restart.bean.Ad;
import com.restart.constant.CauseEnum;
import com.restart.dao.AdDao;
import com.restart.dto.AdDto;
import com.restart.dto.PageResult;
import com.restart.service.AdService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class AdServiceImpl implements AdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdServiceImpl.class);


    @Value(value = "${adImage.url}")
    private String filePath;

    @Value(value = "${adImage.savePath}")
    private String savePath;
    @Autowired
    private AdDao adDao;
    @Override
    public PageResult<AdDto> getAdList(AdDto adDto) {
        LinkedList<AdDto> adDtos = new LinkedList<>();

        Ad condition = new Ad();
        List<Ad> adListByPage = null;
        BeanUtils.copyProperties(adDto, condition);
        try {
            adListByPage = adDao.getAdListByPage(condition);
            for(Ad ad : adListByPage){
                AdDto dto = new AdDto();
                adDtos.add(dto);
                BeanUtils.copyProperties(ad, dto);
                dto.setImgFileName(filePath + ad.getImgFileName());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return  new PageResult<AdDto>(adDtos, condition.getPage());
    }


    @Override
    public boolean update(AdDto adDto) {
        String fileName = null;
        Ad ad = adDao.selectById(adDto.getId());
        if(ad == null){
            throw new BaseException(CauseEnum.AD_NOT_EXIST);
        }
        BeanUtils.copyProperties(adDto,ad);

        try {
            if(CommonUtil.isMultipartFileExist(adDto.getImgFile())){
                fileName = FileUtil.saveFile(adDto.getImgFile(), savePath);
                ad.setImgFileName(fileName);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return  false;
        }
        Long count = adDao.updateAd(ad);
        if(count != 1) return false;

        try {
            if(CommonUtils.isNotEmpty(fileName)){
                FileUtil.deleteFile(savePath + adDto.getImgFileName());
            }
        } catch (Exception e) {
            //如果删除不能回滚，记录，定时删除
            LOGGER.error(e.getMessage() + "fileName:" +adDto.getImgFileName());
            e.printStackTrace();
        }
        return  true;
    }

    @Override
    public boolean addAd(AdDto adDto){
        MultipartFile multiFile = adDto.getImgFile();
        File file = null;
        String fileName = System.currentTimeMillis() + "_" +  multiFile.getOriginalFilename();
        if(CommonUtil.isMultipartFileExist(multiFile)){
            file = new File(savePath + fileName);
        }else {
            throw new BaseException(CauseEnum.FILE_NOT_EXIST);
        }

        File fileFolder = new File(savePath);
        if(!fileFolder.exists()){
            fileFolder.mkdir();
        }
        try {
            multiFile.transferTo(file);
            Ad condition = new Ad();
            BeanUtils.copyProperties(adDto, condition);
            condition.setImgFileName(fileName);
            return adDao.addAd(condition);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public boolean deleteById(Long id) {

        Ad ad = adDao.selectById(id);
        if(ad == null){
            throw new BaseException(CauseEnum.AD_NOT_EXIST);
        }
        Long count = adDao.deleteById(id);
        if(count == 1){
            return  false;
        }

        try {
            FileUtil.deleteFile(savePath + ad.getImgFileName());
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "fileName:" +ad.getImgFileName());
            e.printStackTrace();
            return false;
        }
    }

}
