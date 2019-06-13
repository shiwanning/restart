package com.restart.constant;

public enum CauseEnum {
    FILE_NOT_EXIST("文件不存在");

    private String description;


    public String getDescription() {
        return description;
    }

    CauseEnum(String description) {
        this.description = description;
    }
}
