package com.bolingcavalry.hellojib.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liaohua1
 * @date 2020/4/27 10:53
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
