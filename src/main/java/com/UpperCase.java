package com;

public class UpperCase implements Task {

    private String result;
    private int taskId;

    public UpperCase(int taskId) {
        this.taskId = taskId;
    }

    public void execute() {
        result = "javacode".toUpperCase();
        System.out.println(Thread.currentThread().getName() + " " + getResult() + " from task " + taskId);
    }

    @Override
    public String getResult() {
        return "Uppercase: " + result;
    }
}
