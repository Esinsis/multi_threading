package com.tyler.multi.basic;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author tyler
 * @date 2021/3/7 17:45
 */
@Slf4j
public class MyThread {

    @SneakyThrows
    public static void main(String[] args) {
        new ThreadDemo("t1").start();
        new Thread(new RunnableDemo(), "t2").start();
        FutureTask<String> futureTask = new FutureTask<>(new CallableDemo());
        new Thread(futureTask, "t3").start();
        String result = futureTask.get();
        log.info("The result of future task:{}", result);

        Thread t2 = new Thread(new RunnableDemo(), "t2");
        t2.start();

    }
}

@Slf4j
class ThreadDemo extends Thread {

    public ThreadDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        log.info("extends thread implement");
    }
}

@Slf4j
class RunnableDemo implements Runnable {

    @Override
    public void run() {
        log.info("implement runnable interface...");
    }
}

@Slf4j
class CallableDemo implements Callable<String> {

    @Override
    public String call() {
        log.info("implements callable interface...");
        return "callable";
    }
}


