package com.tyler.multi.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

import static com.tyler.multi.basic.utils.ThreadUtil.sleep;

/**
 * @author tyler
 * @date 2021/5/29 19:04
 */
@Slf4j
public class InterruptParkThreadTest {

    @Test
    public void testInterruptParkThread(){
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
            LockSupport.park();
            log.debug("re-park 之后依然执行了");
        }, "t1");
        t1.start();
        sleep(1);
        t1.interrupt();
        log.debug("{}",t1.getState());
//        sleep(1);
    }
}
