package com.tyler.multi.basic;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author tyler
 * @date 2021/5/28 12:29
 */
@Slf4j(topic = "t.WaitNotifyDemo")
public class WaitNotifyDemo {

    static final Object LOCK = new Object();

    static boolean hasToilet = false;


    @SneakyThrows
    public static void main(String[] args) {
        playLOL();
//        playLOLSmart();
    }

    /**
     * 排在后边的线程一直阻塞，效率低
     */
    @SneakyThrows
    public static void playLOL(){
        new Thread(() -> {
            synchronized (LOCK){
                log.debug("送钱的来了没？");
                while (!hasToilet){
                    log.debug("还没来。。继续等");
                    try {
                        TimeUnit.SECONDS.sleep(1); //必须等完1秒，如果不到一秒时有坑了，还是在傻等
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("送钱来了么？");
                if (hasToilet){
                    log.debug("终于送来了...");
                }
            }
        }, "t1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                synchronized(LOCK){
                    log.debug("五连座走起~");
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(3);

        new Thread(()->{
            // TODO 要不要加synchronized(LOCK) ?
            log.debug("我送钱来了。。。");
            hasToilet = true;
        }).start();
    }

    @SneakyThrows
    public static void playLOLSmart(){
        new Thread(() -> {
            synchronized (LOCK){
                log.debug("送钱的来了没？");
                while (!hasToilet){
                    log.debug("还没来。。继续等");
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("送钱来了么？");
                if (hasToilet){
                    log.debug("终于送来了...");
                }
            }
        }, "t1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                synchronized(LOCK){
                    log.debug("五连座走起~");
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(3);

        new Thread(()->{
            synchronized (LOCK){
                log.debug("我送钱来了。。。");
                hasToilet = true;
                LOCK.notifyAll();// 防止虚假唤醒
            }
        }).start();
    }
}
