package com;

import java.util.LinkedList;

public class BlockingQueue<T> {

    private final LinkedList<T> list = new LinkedList<>();
    private final int size;

    public BlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void enqueue(T el) throws InterruptedException {
        while (list.size() == size) {
            wait();
        }
        list.addLast(el);
        notify();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (list.isEmpty()) {
            wait();
        }
        T value = list.removeFirst();
        notify();
        return value;
    }

    public synchronized int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
