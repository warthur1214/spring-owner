package com.warthur.demo.test.thread;

public class Actress implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "是一个女孩子！");

        int count = 0;
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println(Thread.currentThread().getName() + "登台演出了 " + (++count) + " 次！");
            if (count%10 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count == 100) {
                keepRunning = false;
            }
        }

        System.out.println(Thread.currentThread().getName() + "演出结束了！");
    }

}
