package week7;

public class TwoSignals {

    public static void main(String[] args) {

        Road road = new Road("명전대 사거리");
        Thread people = new Thread(new People(road), "people");
        Thread car = new Thread(new Car(road), "car");

        people.start();
        car.start();
    }

    private static class People implements Runnable {
        Road road;

        public People(Road road) {
            this.road = road;
        }

        @Override
        public void run() {
            road.walk();
        }
    }

    private static class Car implements Runnable {
        Road road;

        public Car(Road road) {
            this.road = road;
        }

        @Override
        public void run() {
            road.drive();
        }
    }

    private static class Road {

        String name;
        String signal = "green";

        public Road(String name) {
            this.name = name;
        }

        public synchronized void walk() {
            while (!(signal.equals("red"))) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            System.out.println(name + "에 "+ Thread.currentThread().getName() +"가 길을 건넌다");
            signal = "green";
            notify();
        }

        public synchronized void drive() {
            while (!(signal.equals("green"))) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            System.out.println(name + "에 "+ Thread.currentThread().getName() +"가 길을 건넌다");
            signal = "red";
            notify();
        }
    }
}
