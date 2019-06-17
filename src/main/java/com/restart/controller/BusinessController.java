package com.restart.controller;


import com.restart.bean.Business;
import com.restart.bean.Page;
import com.restart.dto.BaseResponse;
import com.restart.dto.BusinessDto;
import com.restart.dto.PageBaseResponse;
import com.restart.dto.PageResult;
import com.restart.service.BusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "BusinessController", tags = {"商户接口"})
@RestController
@RequestMapping("/business")
public class BusinessController {


    @Autowired
    private BusinessService businessService;

    @PostMapping
    @ApiOperation(value = "新增商户")
    public BaseResponse addBusiness(BusinessDto businessDto){
        return  new BaseResponse(businessService.insertBusiness(businessDto));
    }


    @GetMapping
    @ApiOperation(value = "获取商户资讯")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size",value = "页数大小", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "keyWord",value = "关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "price",value = "价格", dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "city",value = "城市", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "category",value = "类别", dataType = "String", paramType = "query")
    })
    public BaseResponse<BusinessDto> getBusinessByPageCondition(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) String  keyWord,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false)  String city,
            @RequestParam(required = false)  String category
    ){

        BusinessDto businessDto = new BusinessDto();
        Page pageCondition = businessDto.getPage();
        pageCondition.setCurrentPage(page);
        pageCondition.setPageNumber(size);

        businessDto.setCategory(category);
        businessDto.setCity(city);
        businessDto.setPrice(price);
        businessDto.setKeyWord(keyWord);

        PageResult<BusinessDto> businessByPage = businessService.getBusinessByPage(businessDto);

        return  new PageBaseResponse<BusinessDto>(businessByPage);
    }

    @PutMapping
    public BaseResponse updateBusiness(BusinessDto businessDto){
       return  null;
    }
}
