package com;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(10);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    blockingQueue.enqueue(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();

        Thread consumer = new Thread(() -> {
           try {
               for (int i = 0; i < 10; i++) {
                   blockingQueue.dequeue();
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        });

        consumer.start();
    }
}