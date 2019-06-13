package com.restart.service.impl;

import com.restart.Exception.BaseException;
import com.restart.bean.Ad;
import com.restart.constant.CauseEnum;
import com.restart.dao.AdDao;
import com.restart.dto.AdDto;
import com.restart.dto.PageResult;
import com.restart.service.AdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class AdServiceImpl implements AdService {

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
            e.printStackTrace();
            return  null;
        }
        return  new PageResult<AdDto>(adDtos, condition.getPage());
    }

    @Override
    public boolean addAd(AdDto adDto) throws BaseException {
        MultipartFile multiFile = adDto.getImgFile();
        File file = null;
        String fileName = System.currentTimeMillis() + "_" +  multiFile.getOriginalFilename();
        if(!multiFile.isEmpty() && multiFile.getSize() > 0){
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
            e.printStackTrace();
            return  false;
        }
    }
}
