package com.restart.controller;



import com.restart.constant.BaseStatus;
import com.restart.dto.BaseResponse;
import com.restart.dto.MemberDto;
import com.restart.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@Api(value="ApiController", tags = {"功能型接口"})
public class ApiController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/SMS/")
    @ApiOperation(value = "发送手机短信验证")
    public BaseResponse loginSMS(@RequestParam Long LongUserName){

        memberService.memberSms(LongUserName);

        return  new BaseResponse(BaseStatus.SUCCESS.getCode());
    }


    @PostMapping(value = "/memberLogin/")
    @ApiOperation(value = "会员登录")
    public BaseResponse memberLogin(MemberDto memberDto){

      return  memberService.memberLogin(memberDto);

    }
}
