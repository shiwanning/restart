package com.restart.service;

public interface MemberService {

    boolean memberSms(Long longUserName);

    boolean SmsSendCode(String Code);
}
