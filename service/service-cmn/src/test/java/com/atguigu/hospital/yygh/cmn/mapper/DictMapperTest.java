package com.atguigu.hospital.yygh.cmn.mapper;

import com.atguigu.hospital.yygh.model.cmn.Dict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class DictMapperTest {

        @Autowired
        private DictMapper dictMapper;
        @Test
        public void test(){
            Dict dict = dictMapper.selectById(1);
            System.out.println(dict);
            System.out.println("这是springMappertest");

        }
}
