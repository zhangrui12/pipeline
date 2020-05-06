package com.bolingcavalry.hellojib;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.*;

/**
 * @author liaohua1
 * @date 2020/4/27 10:51
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class HellojibApplication {
    public static void main(String[] args) {
        SpringApplication.run(HellojibApplication.class, args);
    }

    @Bean("callBackThreadPool")
    public ExecutorService callBackThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        ExecutorService threadPool = new ThreadPoolExecutor(
                10, 10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy()
        );
        return threadPool;
    }
}
