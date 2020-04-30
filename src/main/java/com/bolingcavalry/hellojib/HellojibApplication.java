package com.bolingcavalry.hellojib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author liaohua1
 * @date 2020/4/27 10:51
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HellojibApplication {
    public static void main(String[] args) {
        SpringApplication.run(HellojibApplication.class, args);
    }
}
