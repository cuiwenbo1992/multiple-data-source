package com.edu.controller;

import com.edu.mapper.demo1.Demo1Mapper;
import com.edu.mapper.demo2.Demo2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @Autowired
    private Demo1Mapper demo1Mapper;
    @Autowired
    private Demo2Mapper demo2Mapper;

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/test1")
    public String test1 (@RequestParam String username, int age, Double sal) {
        return demo1Mapper.addDemo1(username,age,sal)>0?"success":"fail";
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/test2/{city}")
    public String test2 (@PathVariable("city") String city) {
        String flag = demo2Mapper.addDemo2(city)>0?"success":"fail";
        int a = Integer.valueOf(city);
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/test3")
    public String test3 (@RequestParam String username, int age, Double sal, String city) {
        int i = demo1Mapper.addDemo1(username, age, sal);
        int j = demo2Mapper.addDemo2(city);
        int a = Integer.valueOf(city);
        return j>0?"success":"fail";
    }
}
