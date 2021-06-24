package com.tyler.multi.basic.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author tyler
 * @date 2021/5/28 13:58
 */
public class ThreadUtil {

    public static void sleep(int second){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
