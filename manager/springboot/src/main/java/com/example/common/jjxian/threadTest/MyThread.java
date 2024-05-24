package com.example.common.jjxian.threadTest;

public class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("xianchegn1");
    }
}

class Test01{
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        myThread1.run();
    }
}
