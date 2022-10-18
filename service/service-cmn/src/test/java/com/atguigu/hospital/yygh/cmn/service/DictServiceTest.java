package com.atguigu.hospital.yygh.cmn.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictServiceTest {

    @Autowired
    private DictService dictService;

    @Test
    public void test(){

        long id =10000;
        //List<Dict> childData = dictService.findChildData(id);
        boolean children = dictService.isChildren(id);
        System.out.println(children);

        System.out.println("这是springtest");
    }
}
