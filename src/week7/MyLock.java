package week7;

import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MyLock {

    public static void main(String[] args) {
        Basket basket = new Basket(3);
        Thread[] providers = new Thread[15];
        Thread[] consumers = new Thread[15];

        for (int i = 0; i < 15; i++) {
            providers[i] = new Thread(new Provider(basket));
            consumers[i] = new Thread(new Consumer(basket));
        }

        for (int i = 0; i < 15; i++) {
            providers[i].start();
            consumers[i].start();
        }
    }

    public static class Basket {
        Lock lock = new ReentrantLock();
        Condition consCond = lock.newCondition();
        Condition prodCond = lock.newCondition();

        Queue<Integer> basket = new ArrayDeque<>();
        int max;

        public Basket(int max) {
            this.max = max;
            basket.offer(10);
            basket.offer(7);
            basket.offer(3);
        }

        public void provide() {
            lock.lock();
            try {
                while (basket.size() == max) {
                    System.out.println("바구니가 가득참, 생성자 대기");
                    try {
                        prodCond.await();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("바구니에 숫자 생성");
                basket.offer((int) (Math.random() * 50));
                consCond.signal();
            } finally {
                lock.unlock();
            }
        }

        public void consume() {
            lock.lock();
            try {
                while (basket.isEmpty()) {
                    System.out.println("바구니가 비었음, 소비자 대기");
                    try {
                        consCond.await();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("바구니에서 숫자 꺼내기: " + basket.poll());
                prodCond.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class Provider implements Runnable{
        Basket basket;

        public Provider(Basket basket) {
            this.basket = basket;
        }

        @Override
        public void run() {
            basket.provide();
        }
    }
    private static class Consumer implements Runnable{
        Basket basket;

        public Consumer(Basket basket) {
            this.basket = basket;
        }

        @Override
        public void run() {
            basket.consume();
        }
    }
}
