package ex;

import java.util.Scanner;

public class MusicTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("학과를 입력하세요");
        String dpt = sc.next();

        TestRoom tr = new TestRoom(dpt);

        System.out.println(dpt + " 응시자들의 수를 적어주세요");
        int count = sc.nextInt();
        Tester[] testers = new Tester[count];

        for (int i = 0; i < count; i++) {
            testers[i] = new Tester((i + 1), tr);
        }

        for (Tester tester : testers) {
            tester.start();
        }
    }

    private static class TestRoom {
        private String dpt;
        private int count = 1;
        private String inst = "보컬";

        public TestRoom(String dpt) {
            this.dpt = dpt;
        }

        public synchronized void testStart(int order) {
            while (!(count == order)) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            System.out.println(order + "번 응시자가 " + inst + " 시험 본다");
            if (inst.equals("보컬")) {
                inst = "드럼";
            } else if (inst.equals("드럼")) {
                inst = "기타";
            } else if (inst.equals("기타")) {
                inst = "보컬";
            }
        }
    }

    private static class Tester extends Thread{
        private int order;
        TestRoom room;

        public Tester(int order, TestRoom room) {
            this.order = order;
            this.room = room;
        }

        @Override
        public void run() {

        }
    }
}
