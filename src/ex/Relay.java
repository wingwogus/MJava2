package ex;

import java.util.Scanner;

public class Relay {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("학과명을 입력하세요");
        String dpt = sc.nextLine();

        Baton baton = new Baton(dpt);

        Runner[] runners = new Runner[4];

        for (int i = 0; i < 4; i++) {
            System.out.println((i+1) + "번째 선수의 등번호와 이름을 입력해주세요");
            runners[i] = new Runner(baton, sc.nextInt(), sc.next());
        }

        for (int i = 0; i < 4; i++) {
            runners[i].start();
        }
    }



}
class Baton {
    private int signal = 1;
    private String dpt;
    public Baton(String dpt) {
        this.dpt = dpt;
    }

    public synchronized void takeBaton(Runner r) {
        while (signal != r.order) {
            try {
                wait();
            } catch (InterruptedException e){}
        }
        System.out.println(dpt + " " + r.order + "번 " + r.name + " 선수가 바톤을 들고 달린다");
        signal++;
        notifyAll();
    }

}

class Runner extends Thread {
    Baton baton;
    int order;
    String name;

    public Runner(Baton baton, int no, String name) {
        this.baton = baton;
        this.order = no;
        this.name = name;
    }

    public void run() {
        baton.takeBaton(this);
    }
}
