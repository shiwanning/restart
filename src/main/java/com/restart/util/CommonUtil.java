package com.restart.util;

import org.springframework.web.multipart.MultipartFile;

public class CommonUtil {

    public static boolean isMultipartFileExist(MultipartFile multipartFile){
        return  !multipartFile.isEmpty() && multipartFile.getSize()> 0;
    }
}
