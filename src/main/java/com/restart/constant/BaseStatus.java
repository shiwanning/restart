package com.restart.constant;

public enum BaseStatus {
    SUCCESS(200,"SUCCESS"),
    FAIL(401, "FAIL");

    private Integer Code;

    private String description;


    public Boolean getCode() {
        return Code == 200;
    }
    public String getDescription() {
        return description;
    }

    BaseStatus(Integer code, String description) {
        Code = code;
        this.description = description;
    }
}
