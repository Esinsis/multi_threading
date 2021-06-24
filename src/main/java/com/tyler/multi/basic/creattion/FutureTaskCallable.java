package com.tyler.multi.basic.creattion;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author tyler
 * @date 2021/5/22 14:58
 */
public class FutureTaskCallable {

    @SneakyThrows
    public static void main(String[] args) {
        // 使用线程池方式调用
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        futureTask.cancel(true);
        threadPool.submit(futureTask);
        threadPool.shutdown();

        // 使用new线程方式调用
        Task task1 = new Task();
        FutureTask<Integer> futureTask1 = new FutureTask<>(task1);
        Thread thread = new Thread(futureTask1);
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("Main thread executing the task");
        System.out.printf("the result of task : %s %n", futureTask.get());
        System.out.printf("the result of task started by thread: %s %n", futureTask1.get());
    }
}

