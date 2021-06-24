package com.tyler.multi.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static com.tyler.multi.basic.utils.ThreadUtil.sleep;

/**
 * @author tyler
 * @date 2021/5/29 19:01
 */
@Slf4j(topic = "tyler.InterruptParkDemo")
public class InterruptParkDemo {

    public static void main(String[] args) {
        interruptPark();
    }

    private static void interruptPark(){

        Thread t1 = new Thread(() -> {

            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
            LockSupport.park();
            log.info("re-park 之后依然执行了");
        }, "t1");
        t1.start();
        sleep(1);
        t1.interrupt();
    }
}
