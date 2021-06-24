package com.tyler.multi.basic;

/**
 * 1.线程优先级会提示（hint）调度器优先调度该线程，但它仅仅是一个提示，调度器可以忽略它
 * 2.如果 cpu 比较忙，那么优先级高的线程会获得更多的时间片，但 cpu 闲时，优先级几乎没作用
 *
 * @author tyler
 * @date 2021/3/7 18:03
 */
public class ThreadPriority {

    public static void main(String[] args) {
        Runnable task1 = () -> {
            int count = 0;
            for (; ; ) {
                System.out.println("--->1 " + count++);
            }
        };

        Runnable task2 = () -> {
            int count = 0;
            for (; ; ) {
                System.out.println("            --->2 " + count++);
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
    }
}
