package com.bolingcavalry.hellojib.service;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @author liaohua1
 * @date 2020/5/6 15:53
 */
@Service
public class AccessLimitService {

    RateLimiter rateLimiter = RateLimiter.create(1.0);

    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }



}
