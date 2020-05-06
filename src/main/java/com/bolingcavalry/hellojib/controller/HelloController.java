package com.bolingcavalry.hellojib.controller;

import com.bolingcavalry.hellojib.util.ShellUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * @author liaohua1
 * @date 2020/4/27 10:53
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, nihao, ken" ;
    }

    @RequestMapping("/hello1")
    public String hello1() throws IOException {
        boolean flag = ShellUtil.exec("", String.format("\\cp -r -a %s %s", "/data/registry/docker/registry/v2/repositories/library/elasticsearch", "/whale/release/image/elasticsearch/6.4.0/elasticsearch"));
        if (flag) {
            return "success";
        } else {
            return "false";
        }
    }

}
