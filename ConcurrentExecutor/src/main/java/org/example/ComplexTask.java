package org.example;

public class ComplexTask implements Runnable {
    private final int taskId;

    private int result;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
            result = execute(taskId);
            System.out.println("Task " + taskId + " finished with result  " + result);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int execute(int numberOfTask) {
        return numberOfTask * numberOfTask;
    }

    public int getResult() {
        return result;
    }
}
