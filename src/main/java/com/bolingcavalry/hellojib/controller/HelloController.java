package com.bolingcavalry.hellojib.controller;

import com.bolingcavalry.hellojib.service.AccessLimitService;
import com.bolingcavalry.hellojib.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author liaohua1
 * @date 2020/4/27 10:53
 */
@RestController
public class HelloController {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private AccessLimitService accessLimitService;

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

    @RequestMapping("/access")
    @ResponseBody
    public String access(){
        //尝试获取令牌
        if(accessLimitService.tryAcquire()){
            //模拟业务执行5000毫秒
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "aceess success [" + sdf.format(new Date()) + "]";
        }else{
            return "aceess limit [" + sdf.format(new Date()) + "]";
        }
    }

}
