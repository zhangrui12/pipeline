package com.bolingcavalry.hellojib.thread;

/**
 * @author liaohua1
 * @date 2020/5/19 17:26
 */
public interface MsgLogger {

    /**
     * 异步写入消息
     * @param msg
     */
    void log(String msg) ;


    /**
     * 停止写入
     */
    void stop() ;

}
