package com.warthur.demo.test.thread;

public class KeyPersonThread extends Thread {

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "开始战斗！");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "关键人物厮杀" + (i+1) + "次！");
        }
        System.out.println(Thread.currentThread().getName() + "结束战斗！");
    }
}
