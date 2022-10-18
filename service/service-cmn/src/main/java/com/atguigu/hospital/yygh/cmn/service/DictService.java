package com.atguigu.hospital.yygh.cmn.service;

import com.atguigu.hospital.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {

    //    根据数字id查询子数据列表
    List<Dict> findChildData(Long id);

    /**
     * 导出
     */
    void  exportData(HttpServletResponse response);

    //判断是否为子节点
    boolean isChildren(Long id);


}
