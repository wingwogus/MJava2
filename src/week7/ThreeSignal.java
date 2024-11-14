package week7;

public class  ThreeSignal {

    public static void main(String[] args) {
        Road road = new Road();
        Thread car = new Thread(new Car(road));
        Thread bike = new Thread(new Bike(road));
        Thread person = new Thread(new Person(road));

        car.start();
        bike.start();
        person.start();
    }
        private static class Road {
            String signal = "green";

            public synchronized void drive() {
                while (!(signal.equals("green"))) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("차가 주행한다");
                signal = "yellow";
                notifyAll();
            }

            public synchronized void walk() {
                while (!(signal.equals("red"))) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("보행자가 걷는다");
                signal = "green";
                notifyAll();
            }

            public synchronized void biking() {
                while (!(signal.equals("yellow"))) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("자전거가 주행한다");
                signal = "red";
                notifyAll();
            }
        }

        private static class Car implements Runnable {

            Road road;

            public Car(Road road) {
                this.road = road;
            }

            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    road.drive();
                }
            }
        }
        private static class Bike implements Runnable {

            Road road;

            public Bike(Road road) {
                this.road = road;
            }

            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    road.biking();
                }
            }
        }
        private static class Person implements Runnable {

            Road road;

            public Person(Road road) {
                this.road = road;
            }

            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    road.walk();
                }
            }
        }
    }

