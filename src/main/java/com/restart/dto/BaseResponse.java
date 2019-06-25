package com.restart.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.restart.constant.BaseStatus;

import java.io.Serializable;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {

    private Collection<T> value;

    private Boolean success;

    private String errorMessage;

    private String token;

    public Collection<T> getValue() {
        return value;
    }

    public void setValue(Collection<T> value) {
        this.value = value;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setSuccess(BaseStatus success) {
        this.success = success.getCode();
        if(this.success != BaseStatus.SUCCESS.getCode()){
            this.errorMessage = success.getDescription();
        }
    }

    public BaseResponse(Boolean success) {
        this.success = success;
    }

    public BaseResponse(Collection<T> value){
        this.value = value;
        this.success = true;
    }

    public BaseResponse(String token){
        this.success = true;
        this.token = token;
    }

    public BaseResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
