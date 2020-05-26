package com.bolingcavalry.hellojib.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author liaohua1
 * @date 2020/5/6 15:45
 */
@Component
public class ScheduleTaskTest {

    private Logger logger = LoggerFactory.getLogger(ScheduleTaskTest.class);

    @Scheduled(fixedRate = 10000)
    public void start() {
        /*logger.info("======= 开始做定时任务 ======= 2" + LocalDateTime.now());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> 2");
        logger.info("======= 结束做定时任务 ======= 2" + LocalDateTime.now());*/
    }

}
