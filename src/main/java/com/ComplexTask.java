package com;

// представляет сложную задачу, которую будут выполнять несколько потоков

import java.util.ArrayList;
import java.util.List;

public class ComplexTask implements Runnable {

    private final List<Task> tasks;
    private final int taskId;

    public ComplexTask(int taskId) {
        tasks = new ArrayList<>();
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("starting hard task " + taskId + " in thread: " + Thread.currentThread().getName());
        tasks.forEach(Task::execute);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<String> getResults() {
        List<String> results = new ArrayList<>();
        for (Task task : tasks) {
            results.add(task.getResult());
        }
        return results;
    }
}
