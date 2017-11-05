package com.warthur.demo.test.thread;

public class Actor extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "是一个演员！");

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

    // public static void main(String[] args) {
    //     Thread actor = new Actor();
    //
    //     actor.setName("Mr Thread");
    //     actor.start();
    //
    //     new Thread(new Actress(), "Ms 女士").start();
    // }
}
