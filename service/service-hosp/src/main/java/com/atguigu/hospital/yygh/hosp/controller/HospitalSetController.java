package com.atguigu.hospital.yygh.hosp.controller;

import com.alibaba.excel.util.StringUtils;
import com.atguigu.hospital.yygh.common.result.Result;
import com.atguigu.hospital.yygh.common.utils.MD5;
import com.atguigu.hospital.yygh.hosp.service.HospitalSetService;
import com.atguigu.hospital.yygh.model.hosp.HospitalSet;
import com.atguigu.hospital.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Api(tags = "医院管理设置")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;


    //    1.查询医院设置表所有的信息
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("/findAll")
    public Result findAllHospitalSet(){
        //    调用service的方法
        List<HospitalSet> list = hospitalSetService.list();
        return  Result.ok(list);

    }
    //    2.逻辑删除医院的信息
    @ApiOperation(value = "获取所有医院设置")
    @DeleteMapping("/{id}")
    public  Result removeHospSet(@PathVariable long id){
        //    调用service的方法
        boolean flag = hospitalSetService.removeById(id);
        if(flag){
            return  Result.ok(flag);
        }else {
            return  Result.fail();
        }
    }
    //  3.条件查询分页
    @PostMapping("/findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody (required = false)
                                          HospitalSetQueryVo hospitalSetQueryVo){
        //page对象，传递当前页面，每页记录的条数
        Page<HospitalSet> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();//获取医院的名称
        String hoscode = hospitalSetQueryVo.getHoscode();//获取医院的编号
        //条件判断
        if(!StringUtils.isEmpty(hosname)){
            wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)){
            wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }
        //调用方法实现分页查询
        Page<HospitalSet> hospitalSetPage= hospitalSetService.page(page, wrapper);
        //返回查询结果
        return  Result.ok(hospitalSetPage);

    }

    //   4.添加医院设置
    @PostMapping("/saveHospitalSet")
    public  Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
//    设置状态1使用过，0不能使用
        hospitalSet.setStatus(1);
//    签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        //        调用service
        boolean save = hospitalSetService.save(hospitalSet);
        if (save){
            return  Result.ok();
        }else {
            return  Result.fail();
        }
    }
    //5.根据id获取医院设置
    @GetMapping("/getHospSet/{id}")
    public  Result getHospitalSet(@PathVariable long id){
        HospitalSet hospitalSet  = hospitalSetService.getById(id);
        return  Result.ok(hospitalSet);
    }
    //6.修改医院的信息
    @PostMapping("/updateHospitalSet")
    public  Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag){
            return  Result.ok();
        }else {
            return Result.fail();
        }
    }
    //    7.批量删除医院的信息
    @DeleteMapping("/batchRemove")
    public  Result batchRemoveHospitalSet(@RequestBody List<Long> idList){
        boolean flag = hospitalSetService.removeByIds(idList);
        return  Result.ok();
    }


    //   8.医院的锁定和解锁

    @PutMapping("/lockHospitalSet/{id}/{status}")
    public  Result lockHospitalSet(@PathVariable long id,
                                   @PathVariable Integer status){
        //根据id查询医院设置的状态信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用方法
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }
    //9 发送签名秘钥
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }
}
