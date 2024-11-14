package week3;

import javax.swing.*;
import java.awt.*;

public class TestRunnableMultiThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableThread2("스레드1"));
        Thread t2 = new Thread(new RunnableThread2("스레드2"));

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": start");
        }, "Thread lambda");
 

        System.out.println("스레드 시작 전");
        t1.start();
        t2.start();
        thread.start();
        System.out.println("스레드 시작 후");
    }
}

class RunnableThread2 implements Runnable {

    String name;

    public RunnableThread2(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " 스레드 실행 중입니다");

        }
    }
}