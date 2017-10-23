package com.warthur.demo.test.thread;

public class ArmyRunnable implements Runnable {

    // 保证线程可以正确读取其他线程写入的值
    volatile boolean keepRunning = true;

    @Override
    public void run() {

        while (keepRunning) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "进攻对方" + (i+1) + "次！");

                // 让出了cpu资源
                Thread.yield();
            }

        }

        System.out.println(Thread.currentThread().getName() + "结束了战斗！");
    }
}
