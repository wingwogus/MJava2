package week3;

public class TestThread {

    public static void main(String[] args) {
        SimpleThread simpleThread1 = new SimpleThread("thread1");

        System.out.println("스레드 시작 전");
        simpleThread1.start();
        try {
            simpleThread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("스레드 시작 후");
    }
}
