package com.tyler.multi.basic;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author tyler
 * @date 2021/3/7 18:07
 */
@Slf4j(topic = "ThreadJoinDemo")
public class ThreadJoinDemo {

    static int r = 0;
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) {
//        test1();
//        joinApply();
        timeJoinApply();
    }

    @SneakyThrows
    private static void timeJoinApply() {
        log.info("start...");
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        });

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // r1 = 26 ?
            r2 = 26;
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
//        t2.join(1000);
        t2.join();
        t1.join();
        long end = System.currentTimeMillis();
        log.info("r1 : {}, r2 : {}, cost : {}", r1, r2, end - start);
    }

    private static void test1() {
        log.info("start...");
        Thread t1 = new Thread(() -> {
            log.info("t1 start...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("end...");
            r = 10;
        });

        t1.start();
        log.info("The result of r : {}", r);
        log.info("end...");
    }

    @SneakyThrows
    private static void joinApply() {
        log.info("start...");
        Thread t1 = new Thread(() -> {
            log.info("t1 start...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("end...");
            r = 10;
        });

        t1.start();
        t1.join();
        log.info("the result of r : {}", r);
    }


}
