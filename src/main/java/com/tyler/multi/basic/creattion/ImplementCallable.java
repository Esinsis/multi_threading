package com.tyler.multi.basic.creattion;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @author tyler
 * @date 2021/5/22 14:08
 */
public class ImplementCallable {

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Task task = new Task();
        Future<Integer> future = threadPool.submit(task);
        threadPool.shutdown();


        TimeUnit.SECONDS.sleep(1);
        System.out.println("the main thread execute task");
        System.out.printf("The result of task : %s %n", future.get());
    }
}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("calculating in sub thread");
        TimeUnit.SECONDS.sleep(3);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
