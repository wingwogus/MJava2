package week6;

import java.util.Scanner;

public class ThreeSignal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Road road = new Road("명전대 사거리");
        System.out.println("도로를 이용할 자동차, 자전거, 보행자 수를 입력 ->");
        int cnt = sc.nextInt();
        Car[] cars = new Car[cnt];
        Bike[] bikes = new Bike[cnt];
        Person[] people = new Person[cnt];

        for (int i = 0; i < cnt; i++) {
            cars[i] = new Car(i + 1, road);
            bikes[i] = new Bike(i + 1, road);
            people[i] = new Person(i + 1, road);
        }

        for (int i = 0; i < cnt; i++) {
            cars[i].start();
            bikes[i].start();
            people[i].start();
        }

    }

    private static class Road {
        private String name;
        private final int RED = 0;
        private final int YELLOW = 1;
        private final int GREEN = 2;
        private int signal = GREEN;
        int order = 1;

        public Road(String name) {
            this.name = name;
        }

        public synchronized void drive(int num) {
            while (!(signal == GREEN && order == num)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(name + " 도로에 " + num + "번 차가 주행한다");
            signal = YELLOW;
            notifyAll();
        }

        public synchronized void biking(int num) {
            while (!(signal == YELLOW && order == num)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(name + " 도로에 " + num + "번 자전거가 주행한다");
            signal = RED;
            notifyAll();
        }

        public synchronized void walk(int num) {
            while (!(signal == RED && order == num)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(name + " 도로에 " + num + "번 보행자가 지나간다");
            signal = GREEN;
            order++;
            notifyAll();
        }
    }

    private static class Car extends Thread{
        int number;
        Road road;

        public Car(int number, Road road) {
            this.number = number;
            this.road = road;
        }

        @Override
        public void run() {
            road.drive(number);
        }
    }

    private static class Bike extends Thread{

        int number;

        Road road;
        public Bike(int number, Road road) {
            this.number = number;
            this.road = road;
        }

        @Override
        public void run() {
            road.biking(number);
        }
    }
    private static class Person extends Thread{

        int number;

        Road road;
        public Person(int number, Road road) {
            this.number = number;
            this.road = road;
        }

        @Override
        public void run() {
            road.walk(number);
        }
    }
}
