package com;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int n = 5;

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = (long) forkJoinPool.invoke(factorialTask);


        System.out.println("Факториал " + n + "! = " + result);
    }
}