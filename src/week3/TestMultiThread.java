package week3;

public class TestMultiThread {
    public static void main(String[] args) {
        ThreadA thread1 = new ThreadA("Thread1");
        ThreadA thread2 = new ThreadA("Thread2");
        thread1.start();
        thread2.start();
    }
}

class ThreadA extends Thread {
    public static int num = 0;
    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(num++ + " : " + getName());
        }
    }
}