package com.atguigu.hospital.yygh.cmn.controller;

import com.atguigu.hospital.yygh.cmn.service.DictService;
import com.atguigu.hospital.yygh.common.result.Result;
import com.atguigu.hospital.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(description = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin    //解决跨域
public class DictController {
    @Autowired
    private DictService dictService;

    // 根据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("/findChildData/{id}")
    public Result findChilData(@PathVariable long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出")
    @GetMapping(value = "/exportData")
public  void  exportData(HttpServletResponse response){
        dictService.exportData(response);
}

}
