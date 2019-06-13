package com.restart.dto;

import com.restart.bean.Ad;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@ApiModel(value = "广告",description = "广告")
public class AdDto extends Ad implements Serializable {


    @ApiModelProperty(value="文件",required = true)
    private MultipartFile imgFile;

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
