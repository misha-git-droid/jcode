package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {

    private final List<String> results = Collections.synchronizedList(new ArrayList<>());

    public void executeTasks(int numberOfTasks) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, ()-> {
            System.out.println("tasks completed! results: ");
            results.forEach(System.out::println);
        });

        for (int i = 1; i <= numberOfTasks; i++) {
            int taskId = i;
            executor.submit(() -> {
                try {
                    ComplexTask complexTask = new ComplexTask(taskId);
                    complexTask.addTask(new SumValues(taskId));
                    complexTask.addTask(new UpperCase(taskId));
                    complexTask.run();

                    String threadResult = Thread.currentThread().getName() + ":" +
                            complexTask.getResults();

                    results.add(threadResult);
                    barrier.await();

                    System.out.println(Thread.currentThread().getName() + " completed resume");
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();

    }
}
