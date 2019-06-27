package com.restart.service;

import com.restart.dto.BaseResponse;
import com.restart.dto.MemberDto;
import com.restart.dto.OrderMemberDto;

public interface MemberService {

    boolean memberSms(Long longUserName);

    boolean SmsSendCode(String Code);

    MemberDto findMember(Long longUserName);

    BaseResponse memberLogin(MemberDto memberDto);

    Long findMemberByCacheToken(String token);

    boolean orderToBuy(OrderMemberDto orderMemberDto);
}
