package myCode;

import java.util.Scanner;

public class MakeBread {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("오늘 만들 빵의 개수: ");

        int breadCount = sc.nextInt();

        Bread[] completeBreads = new Bread[breadCount];

        for (int i = 0; i < breadCount; i++) {
            completeBreads[i] = new Bread(i + 1);
        }

        DoughMan doughMan = new DoughMan(completeBreads, "반죽 담당");
        BakingMan bakingMan = new BakingMan(completeBreads, "굽기 담당");
        PackingMan packingMan = new PackingMan(completeBreads, "포장 담당");

        bakingMan.start();
        doughMan.start();
        packingMan.start();
    }
}

class Bread{
    int order;
    String process = "dough";

    public Bread(int order) {
        this.order = order;
    }

    public synchronized void dough(String name) {
        while (!(process.equals("dough"))) {
            try {
                wait();
            } catch(Exception e){}
        }
        System.out.println(name + "이 " + order + "번째 빵을 반죽하고 있습니다");
        process = "baking";
        notifyAll();
    }

    public synchronized void baking(String name) {
        while (!(process.equals("baking"))) {
            try {
                wait();
            } catch(Exception e){}
        }
        System.out.println(name + "이 " + order + "번째 빵을 굽고 있습니다");
        process = "packing";
        notifyAll();
    }

    public synchronized void packing(String name) {
        while (!(process.equals("packing"))) {
            try {
                wait();
            } catch(Exception e){}
        }
        System.out.println(name + "이 " + order + "번째 빵을 포장하고 있습니다");
        process = "dough";
        notifyAll();
    }
}

class DoughMan extends Thread {
    Bread[] breads;

    public DoughMan(Bread[] breads, String name) {
        super(name);
        this.breads = breads;
    }

    public void run() {
        for (Bread bread : breads) {
            bread.dough(getName());
        }
    }
}

class BakingMan extends Thread {
    Bread[] breads;

    public BakingMan(Bread[] breads, String name) {
        super(name);
        this.breads = breads;
    }

    public void run() {
        for (Bread bread : breads) {
            bread.baking(getName());
        }
    }
}
class PackingMan extends Thread {
    Bread[] breads;

    public PackingMan(Bread[] breads, String name) {
        super(name);
        this.breads = breads;
    }

    public void run() {
        for (Bread bread : breads) {
            bread.packing(getName());
        }
    }
}