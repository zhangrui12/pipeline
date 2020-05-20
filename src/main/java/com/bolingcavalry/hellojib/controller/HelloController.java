package com.bolingcavalry.hellojib.controller;

import com.bolingcavalry.hellojib.service.AccessLimitService;
import com.bolingcavalry.hellojib.service.AsyncService;
import com.bolingcavalry.hellojib.util.ShellUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private AccessLimitService accessLimitService;

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, nihao, liaohua" ;
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

    @RequestMapping("/async")
    public String submit(){
        logger.info("start submit");

        //启动一个异步任务
        asyncService.execute();

        logger.info("end submit");

        return "success";
    }

}
