package week4;

public class SyncPcThread {
    public static void main(String[] args) {
        Printer printer = new Printer();

        new Thread(new Pc(printer), "PC1").start();
        new Thread(new Pc(printer), "PC2").start();
        new Thread(new Pc(printer), "PC3").start();
    }


    static class Pc implements Runnable {

        Printer printer;

        public Pc(Printer printer) {
            this.printer = printer;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                printer.printing(Thread.currentThread().getName());
            }
        }
    }
    static class Printer {
        static int num = 0;

        synchronized void printing(String name) {
            System.out.println(name + ": " + num++ + "번째로 출력");
        }
    }
}


