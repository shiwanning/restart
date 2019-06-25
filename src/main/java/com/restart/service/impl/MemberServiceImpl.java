package com.restart.service.impl;


import com.restart.Exception.BaseException;
import com.restart.bean.Member;
import com.restart.cache.CodeCache;
import com.restart.cache.TokenCache;
import com.restart.constant.CauseEnum;
import com.restart.dao.MemberDao;
import com.restart.dto.BaseResponse;
import com.restart.dto.MemberDto;
import com.restart.service.MemberService;
import com.restart.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService .class);

    @Autowired
    private MemberDao memberDao;

    public boolean memberSms(Long longUserName) {
        List<Member> members = memberDao.findMemberByUserName(longUserName);
        if(members.size() == 1){
            String code = CommonUtil.genearateNumbers(6).toString();
            CodeCache instance = CodeCache.getInstance();
            if (!instance.save(longUserName, code)) {
                throw  new BaseException(CauseEnum.REPEAT_REQUEST);
            }
            if(!SmsSendCode(code)){
                throw  new BaseException(CauseEnum.SEND_FAIL);
            }
            return true;

        }else {
            throw new BaseException(CauseEnum.MEMBER_NOT_EXIST);
        }
    }

    @Override
    public BaseResponse memberLogin(MemberDto memberDto) {
        if(memberDto != null) {
            Long phone = memberDto.getPhone();
            MemberDto member = findMember(phone);
            if (member == null) {
                throw new BaseException(CauseEnum.MEMBER_NOT_EXIST);
            }
            String smsCode = memberDto.getSmsCode();
            if (!smsCode.equals(CodeCache.getInstance().getCode(phone))) {
                throw new BaseException(CauseEnum.VERIFY_FAIL);
            }
            String token = CommonUtil.genearateToken();
            TokenCache.getInstance().setToken(phone, token);
            return new BaseResponse(token);
        }
        throw new BaseException(CauseEnum.MEMBER_NOT_EXIST);
    }

    @Override
    public boolean SmsSendCode(String code) {
        LOGGER.info(code);
        return  true;
    }

    @Override
    public MemberDto findMember(Long longUserName) {
        return null;
    }
}
