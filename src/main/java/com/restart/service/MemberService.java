package com.restart.service;

import com.restart.dto.BaseResponse;
import com.restart.dto.MemberDto;

public interface MemberService {

    boolean memberSms(Long longUserName);

    boolean SmsSendCode(String Code);

    MemberDto findMember(Long longUserName);

    BaseResponse memberLogin(MemberDto memberDto);
}
