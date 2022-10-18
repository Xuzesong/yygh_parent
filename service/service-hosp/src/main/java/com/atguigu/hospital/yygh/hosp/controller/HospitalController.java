package com.atguigu.hospital.yygh.hosp.controller;

import com.atguigu.hospital.yygh.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hosp")
@Api("医院模块")
public class HospitalController {


    /**
     * 上传医院接口
     */
    @ApiOperation("上传医院信息")
    @PostMapping("/saveHospital")
    public Result saveHospital(RequestParam request){


        //根据id判断查询是否存在
        //查询密钥是否一致
        //查询成功后
        //查询失败返回原因
        return  null;
    }



}
