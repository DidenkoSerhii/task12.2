package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {
    private static final int N = 15;
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> fizz());
        Thread threadB = new Thread(() -> buzz());
        Thread threadC = new Thread(() -> fizzbuzz());
        Thread threadD = new Thread(() -> number());

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void fizz() {
        for (int i = 1; i <= N; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                queue.add("fizz");
            }
        }
    }

    private static void buzz() {
        for (int i = 1; i <= N; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                queue.add("buzz");
            }
        }
    }

    private static void fizzbuzz() {
        for (int i = 1; i <= N; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                queue.add("fizzbuzz");
            }
        }
    }

    private static void number() {
        for (int i = 1; i <= N; i++) {
            if (!queue.contains("fizz") && !queue.contains("buzz") && !queue.contains("fizzbuzz")) {
                queue.add(String.valueOf(i));
            }
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}