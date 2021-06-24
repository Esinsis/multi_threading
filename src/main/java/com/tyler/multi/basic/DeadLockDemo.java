package com.tyler.multi.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * 古希腊七贤 哲学家一起吃饭
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        testDeadLock();
    }

    public static void testDeadLock(){
        Chopstick ch1 = new Chopstick("1");
        Chopstick ch2 = new Chopstick("2");
        Chopstick ch3 = new Chopstick("3");
        Chopstick ch4 = new Chopstick("4");
        Chopstick ch5 = new Chopstick("5");
        Chopstick ch6 = new Chopstick("6");
        Chopstick ch7 = new Chopstick("7");

        new Philosopher("苏格拉底", ch1, ch2).start();
        new Philosopher("柏拉图", ch2, ch3).start();
        new Philosopher("亚里士多德", ch3, ch4).start();
        new Philosopher("赫拉克利特", ch4, ch5).start();
        new Philosopher("阿基米德", ch5, ch6).start();
        new Philosopher("泰勒士", ch6, ch7).start();
        new Philosopher("毕达哥拉斯", ch7, ch1).start();
//        new Philosopher("毕达哥拉斯", ch1, ch7).start();
    }
}

class Chopstick {

    private String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "name='" + name + '\'' +
                '}';
    }
}

@Slf4j
class Philosopher extends Thread {

    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    private void eat() {
        log.debug("eating...");
    }

    @Override
    public void run() {
        while (true) {
            // 获取左手筷子
            synchronized (left) {
                // 获取右手筷子
                synchronized (right) {
                    eat();
                }
            }
        }
    }
}
