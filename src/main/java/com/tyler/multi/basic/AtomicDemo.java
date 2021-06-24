package com.tyler.multi.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class AtomicDemo {

    static int counter = 0;
    static AtomicInteger ATOMIC_COUNT = new AtomicInteger(0);
    static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                for (int i = 0; i < 5000; i++) {
                    counter++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                for (int i = 0; i < 5000; i++) {
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
        log.debug("atomic count: {}", ATOMIC_COUNT.get());
    }

}
