package com.bolingcavalry.hellojib.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author liaohua1
 * @date 2020/5/6 15:38
 */
@Component
public class ScheduleTask {

    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    /**
    @Scheduled(fixedRate = 3000) ：上一次开始执行时间点之后 3 秒再执行（fixedRate 属性：
    定时任务开始后再次执行定时任务的延时（需等待上次定时任务完成），单位毫秒）
    @Scheduled(fixedDelay = 3000) ：上一次执行完毕时间点之后 3 秒再执行（fixedDelay 属性：
    定时任务执行完成后再次执行定时任务的延时（需等待上次定时任务完成），单位毫秒）
    @Scheduled(initialDelay = 1000, fixedRate = 3000) ：第一次延迟1秒后执行，
    之后按fixedRate的规则每 3 秒执行一次（initialDelay 属性：第一次执行定时任务的延迟时间，需配合fixedDelay或者fixedRate来使用）
    @Scheduled(cron="0 0 2 1 * ? *") ：通过cron表达式定义规则*/
    @Scheduled(fixedRate = 10000)
    public void start() {
        logger.info("======= 开始做定时任务 ======= 1" + LocalDateTime.now());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> 1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("======= 结束做定时任务 ======= 1" + LocalDateTime.now());
    }



}
