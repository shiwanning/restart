package com.restart.controller;


import com.restart.Exception.BaseException;
import com.restart.constant.BaseStatus;
import com.restart.dto.AdDto;
import com.restart.dto.BaseResponse;
import com.restart.dto.PageBaseResponse;
import com.restart.dto.PageResult;
import com.restart.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value="ApiController: ",tags = {"Api接口"})
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AdService adService;


    @GetMapping(value="/getAd")
    @ApiOperation(value = "/获取广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页",required = true,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="页数",required  = true,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="id",value="id",dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name="title",value="标题",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="weight",value="权重",dataType = "Long",paramType = "query")
    })
    public PageBaseResponse<AdDto> getAd(Integer page, Integer size, Long id, String title, Long weight){

        PageBaseResponse<AdDto> adBaseResponse = new PageBaseResponse<AdDto>();
        AdDto adDto = new AdDto();
        adDto.getPage().setCurrentPage(page);
        adDto.getPage().setPageNumber(size);
        adDto.setId(id);
        adDto.setTitle(title);
        adDto.setWeight(weight);

        PageResult<AdDto> adList = adService.getAdList(adDto);
        adBaseResponse.setPageResult(adList);
        adBaseResponse.setSuccess(BaseStatus.SUCCESS);

        return  adBaseResponse;
    }

    @PostMapping(value="/addFile")
    @ApiOperation(value = "上传广告文件")
    public BaseResponse addAd(AdDto adDto){

        adService.addAd(adDto);
        return new BaseResponse(BaseStatus.SUCCESS.getCode());
    }

    @DeleteMapping(value="/deleteAdById/{id}")
    @ApiImplicitParam(name = "id",value = "广告ID", required = true, dataType = "Long",paramType = "path")
    @ApiOperation(value="删除广告")
    public BaseResponse deleteAdById(@PathVariable Long id){

        adService.deleteById(id);
        return new BaseResponse(BaseStatus.SUCCESS.getCode());

    }

}
