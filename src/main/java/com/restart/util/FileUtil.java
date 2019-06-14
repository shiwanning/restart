package com.restart.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {


    public static File getFile(String fileName, String savePath){

        String saveFileName = System.currentTimeMillis()+ "_" + fileName;

        File file = new File(savePath + saveFileName);

        if(file.exists()){
            return  getFile(fileName, savePath);
        }
        return  file;
    }

    public static String saveFile(MultipartFile multipartFile, String savePath){

        String fileName = null;

        if(multipartFile != null && multipartFile.getSize() > 0){
            File file = getFile(multipartFile.getOriginalFilename(), savePath);
            File fileFolder = new File(savePath);
            if(!fileFolder.exists()){
                fileFolder.mkdir();
            }
            try {
                multipartFile.transferTo(file);
                fileName = file.getName();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  fileName;
    }

    public static  boolean deleteFile(String fileName){
        File file = new File(fileName);
        if(file.isFile()){
            file.delete();
            return true;
        }
        return false;
    }
}
