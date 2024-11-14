package week3;

import javax.swing.*;

public class TestRunnableThread {
    public static void main(String[] args) {
        RunnableThread r = new RunnableThread();
        Thread t = new Thread(r);
        System.out.println("스레드 시작 전");
        t.start();
        System.out.println("스레드 시작 후");
    }
}

class RunnableThread extends JFrame implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable 스레드 실행 중입니다");
    }
}
