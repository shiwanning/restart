package com.restart.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class CommonUtil {

    //判断上传的文件是否存在
    public static boolean isMultipartFileExist(MultipartFile multipartFile){
        return  !multipartFile.isEmpty() && multipartFile.getSize()> 0;
    }

    //生成随机数
    public static Integer genearateNumbers(int number){
        return  (int)((Math.random()*9 + 1) * Math.pow(10, number));
    }

    //生成token

    public static String genearateToken(){
       return UUID.randomUUID().toString().replace("-", "");
    }
}
