package com.tyler.multi.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static com.tyler.multi.basic.utils.ThreadUtil.sleep;

/**
 * @author tyler
 * @date 2021/5/28 13:56
 */
@Slf4j(topic = "t.ParkUnparkDemo")
public class ParkUnparkDemo {

    public static void main(String[] args) {
//        basicUsage();
        unparkFirstly();
    }

    public static void basicUsage(){
        Thread t1 = new Thread(() -> {
            log.info("start...");
            sleep(1);
            log.info("parking...");
            LockSupport.park();
            log.info("resume...");
        }, "t1");
        t1.start();

        sleep(2);
        log.info("unpark...");
        LockSupport.unpark(t1);
    }

    /**
     * unpark可以先调用
     */
    public static void unparkFirstly(){
        Thread t1 = new Thread(() -> {
            log.info("start...");
            sleep(3);
            log.info("parking...");
            LockSupport.park();
            log.info("resume...");
        }, "t1");
        t1.start();

        sleep(1);
        log.info("unpark...");
        LockSupport.unpark(t1);
    }

}
