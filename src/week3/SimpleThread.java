package week3;

public class SimpleThread extends Thread{

    public SimpleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " 스레드 실행 중 " + getPriority());
        System.out.println(getName() + " 스레드 실행 단계 1");
        System.out.println(getName() + " 스레드 실행 단계 2");
        System.out.println(getName() + " 스레드 종료");
    }
}
