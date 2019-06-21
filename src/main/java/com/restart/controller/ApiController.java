package com.restart.controller;


import com.restart.constant.BaseStatus;
import com.restart.dto.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="ApiController", tags = {"功能型接口"})
public class ApiController {

    @PostMapping
    @ApiOperation(value = "手机号码注册")
    public BaseResponse loginSMS(@RequestParam String LongUserName){



        return  new BaseResponse(BaseStatus.SUCCESS.getCode());
    }
}
