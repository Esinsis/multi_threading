package com.tyler.multi.basic.creattion;

/**
 * @author tyler
 * @date 2021/5/22 12:18
 */
public class InheritThreadClass {

    public static void main(String[] args) {
        InheritThread t1 = new InheritThread("t1");
        t1.start();
    }
}

class InheritThread extends Thread {

    public InheritThread(){

    }

    public InheritThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("I am created by inheriting Thread class");
    }
}
