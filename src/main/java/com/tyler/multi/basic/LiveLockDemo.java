package com.tyler.multi.basic;

import lombok.extern.slf4j.Slf4j;

import static com.tyler.multi.basic.utils.ThreadUtil.sleep;

@Slf4j
public class LiveLockDemo {

    static volatile int count = 20;
    static final Object LOCK = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            while (count > 0) {
                sleep(1);
                count--;
                log.debug("t1 -> count: {}", count);
            }
        },"t1").start();

        new Thread(()->{
            while (count > 0) {
                sleep(1);
                count++;
                log.debug("t2 -> count: {}", count);
            }
        },"t2").start();
    }
}
