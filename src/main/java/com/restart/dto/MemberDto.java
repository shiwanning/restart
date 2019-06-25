package com.restart.dto;

import com.restart.bean.Member;

import java.io.Serializable;

public class MemberDto extends Member implements Serializable {

    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
