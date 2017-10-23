package com.warthur.demo.test.thread;

public class Stage extends Thread {

    @Override
    public void run() {

        System.out.println("------------战争开始---------");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArmyRunnable armyTaskOfSui = new ArmyRunnable();
        ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();

        Thread armyOfSui = new Thread(armyTaskOfRevolt, "隋军");
        Thread armyOfRevolt = new Thread(armyTaskOfRevolt, "起义军");

        armyOfSui.start();
        armyOfRevolt.start();

        // 主线程休眠，army线程工作
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("正当双方激战正酣，半路杀出关键人物！");

        Thread cheng = new KeyPersonThread();
        cheng.setName("程先生");

        // 主线程唤醒，终止army线程
        armyTaskOfSui.keepRunning = false;
        armyTaskOfRevolt.keepRunning = false;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cheng.start();

        // 其他线程释放资源，cheng线程掌握资源，直到次线程运行结束
        try {
            cheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------战争结束---------");
    }

    public static void main(String[] args) {
        new Stage().start();
    }
}
