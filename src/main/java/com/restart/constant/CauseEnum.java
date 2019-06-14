package com.restart.constant;

public enum CauseEnum {
    FILE_NOT_EXIST("文件不存在"),
    AD_NOT_EXIST("广告不存在");

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    CauseEnum(String errorCode) {
        this.errorCode = errorCode;
    }
}
