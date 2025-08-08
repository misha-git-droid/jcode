package com;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // cyclicBarrier и executorService для синхронизации нескольких потоков
        // выполняющих сложную задачу и затем ожидающих пока все потоки
        // завершат выполнение, чтобы объединить результаты

        ComplexTaskExecutor complexTaskExecutor = new ComplexTaskExecutor();
        complexTaskExecutor.executeTasks(5);

    }
}