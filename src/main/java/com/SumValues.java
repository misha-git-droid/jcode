package com;

public class SumValues implements Task {

    private int result;
    private int taskId;

    public SumValues(int taskId) {
        this.taskId = taskId;
    }


    @Override
    public void execute() {
        int sum = 0;
        for (int i = 1; i <= 10; i++) sum += i;
        result = sum;
        System.out.println(Thread.currentThread().getName() + " " + getResult() + " from task " + taskId);
    }

    @Override
    public String getResult() {
        return "Sum: " + result;
    }
}
