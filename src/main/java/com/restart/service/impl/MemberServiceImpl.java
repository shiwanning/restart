package com.restart.service.impl;


import com.restart.Exception.BaseException;
import com.restart.bean.Member;
import com.restart.bean.Orders;
import com.restart.cache.CodeCache;
import com.restart.cache.TokenCache;
import com.restart.constant.CauseEnum;
import com.restart.constant.OrderStatus;
import com.restart.dao.MemberDao;
import com.restart.dao.OrdersDao;
import com.restart.dto.BaseResponse;
import com.restart.dto.MemberDto;
import com.restart.dto.OrderDto;
import com.restart.dto.OrderMemberDto;
import com.restart.service.MemberService;
import com.restart.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private OrdersDao ordersDao;

    public boolean memberSms(Long longUserName) {
        memberDao.findMemberByUserName(longUserName);
        String code = CommonUtil.genearateNumbers(6).toString();
        CodeCache instance = CodeCache.getInstance();
        if (!instance.save(longUserName, code)) {
            throw  new BaseException(CauseEnum.REPEAT_REQUEST);
        }
        if(!SmsSendCode(code)){
            throw  new BaseException(CauseEnum.SEND_FAIL);
        }
        return true;
    }

    @Override
    public BaseResponse memberLogin(MemberDto memberDto) {
        if(memberDto != null) {
            Long phone = memberDto.getPhone();
            findMember(phone);
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
        MemberDto memberDto = new MemberDto();
        List<Member> memberByUserName = memberDao.findMemberByUserName(longUserName);
        if(memberByUserName.size() == 1){
            Member member = memberByUserName.get(0);
            BeanUtils.copyProperties(member, memberDto);
            return memberDto;
        }
        throw new BaseException(CauseEnum.MEMBER_NOT_EXIST);
    }

    @Override
    public Long findMemberByCacheToken(String token) {
        TokenCache instance = TokenCache.getInstance();
        return instance.getToken(token);
    }

    @Override
    public boolean orderToBuy(OrderMemberDto orderMemberDto) {

        Long phone = findMemberByCacheToken(orderMemberDto.getToken());

        if(phone != null && phone.equals(orderMemberDto.getUserName())){

            MemberDto member = findMember(phone);
            if(member != null){

                Orders orders = new Orders();
                orders.setBusinessId(orderMemberDto.getId());
                orders.setPrice(orderMemberDto.getPrice());
                orders.setNum(orderMemberDto.getNumber());
                orders.setMemberId(member.getId());
                orders.setCommentState(OrderStatus.NOT_COMMENT);
                return ordersDao.insertOrder(orders) == 1;
            }
            throw new BaseException(CauseEnum.MEMBER_NOT_EXIST);
        }
        throw new BaseException(CauseEnum.MEMBER_NOT_LOGIN);
    }
}
