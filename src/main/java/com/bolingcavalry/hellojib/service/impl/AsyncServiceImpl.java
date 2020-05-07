package com.bolingcavalry.hellojib.service.impl;

import com.bolingcavalry.hellojib.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author liaohua1
 * @date 2020/5/6 17:36
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    private static Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("asyncServiceExecutor")
    public void execute() {
        logger.info("start executeAsync");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("end executeAsync");
    }
}
