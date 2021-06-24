package com.tyler.multi.basic;

import lombok.extern.slf4j.Slf4j;

import static com.tyler.multi.basic.utils.ThreadUtil.sleep;

/**
 * 打断 sleep 的线程
 *
 * @author tyler
 * @date 2021/3/7 20:11
 */
@Slf4j
public class InterruptedDemo {

    public static void main(String[] args) throws InterruptedException {
//        interruptTest();
//        interruptPark();
        interruptRunningThread();
//        interruptSleepingThread();
//        test();
    }

    /**
     * 虽然线程处于睡眠状态被打断，但是由于interrupt方法会清楚sleeping线程的打断标记，一次if判断无法成立，程序一直在while循环中
     * 处理方法: 在catch中调用Thread.currentThread().interrupt()重新设置线程的打断标记
     */
    private static void interruptTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    log.info("线程被中断，程序退出。");
                    return;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.info("exception==>线程休眠被中断，程序退出。");
//                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }


    /**
     * 打断sleep、wait、join和park的线程，会清空打断标记
     */
    private static void interruptSleepingThread() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.info("捕获中断异常");
                    log.info("确认线程是否被中断:{}", Thread.currentThread().isInterrupted());
//                    Thread.currentThread().interrupt();
                    log.info("是否被self打断: {}", Thread.currentThread().isInterrupted());
                    break;
                }
            }
        }, "t1");
        t1.start();
        sleep(1);
        t1.interrupt();
//        Thread.sleep(100);
        log.debug("打断状态: {}", t1.isInterrupted());
    }

    /**
     * 打断正常运行的线程，打断标记不会被清楚
     */
    private static void interruptRunningThread() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                log.info("        ==> 打断状态: {}", interrupted);
                if (interrupted) {
                    log.info("线程被打断");
                    log.info("打断状态 ==> : {}", Thread.currentThread().isInterrupted());//true
//                    throw new RuntimeException("a");
                    break;
                }
            }
        }, "t2");
        t2.start();
        log.debug("interrupt...");
        t2.interrupt();
        log.debug("interrupted...");
//        Thread.sleep(1);
        log.info("t2的打断状态: {},{}", t2.getState(), t2.isInterrupted()); //false
    }
}
