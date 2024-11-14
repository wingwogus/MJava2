package ex;


import java.util.ArrayList;
import java.util.Scanner;

public class MusicTest2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("학과명을 입력하세요. =>");
        String dept = sc.next();
        System.out.print(dept + "응시자들의 수를 입력하세요. =>");
        int count = sc.nextInt();

        Room room = new Room(dept);

        Thread[] musicians = new Thread[count];

        for (int i = 0; i < count; i++) {
            if (i % 3 == 0) {
                musicians[i] = new Vocal(i + 1, room);
            } else if (i % 3 == 1) {
                musicians[i] = new Drum(i + 1, room);
            } else {
                musicians[i] = new Guitar(i + 1, room);
            }
        }

        for (int i = 0; i < count; i++) {
            musicians[i].start();
        }
    }


}

class Room{
    String dept;
    int signal = 1;
    int order = 1;

    public Room(String dept) {
        this.dept = dept;
    }

    public synchronized void vocalTest(int o) {
        while (signal != 1 || order != o) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        System.out.println(dept + " " + o + "번 응시자가 보컬 시험 본다");
        order++;
        signal = 2;
        notifyAll();
    }
    public synchronized void drumTest(int o) {
        while (signal != 2 || order != o) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        System.out.println(dept + " " + o + "번 응시자가 드럼 시험 본다");
        order++;
        signal = 3;
        notifyAll();
    }
    public synchronized void guitarTest(int o) {
        while (signal != 3 || order != o) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        System.out.println(dept + " " + o + "번 응시자가 기타 시험 본다");
        order++;
        signal = 1;
        notifyAll();
    }

}

class Vocal extends Thread {
    int order;
    Room room;

    public Vocal(int order, Room room) {
        this.order = order;
        this.room = room;
    }

    @Override
    public void run() {
        room.vocalTest(order);
    }
}

class Drum extends Thread {
    int order;
    Room room;

    public Drum(int order, Room room) {
        this.order = order;
        this.room = room;
    }

    @Override
    public void run() {
        room.drumTest(order);
    }
}

class Guitar extends Thread {
    int order;
    Room room;

    public Guitar(int order, Room room) {
        this.order = order;
        this.room = room;
    }

    @Override
    public void run() {
        room.guitarTest(order);
    }
}
