package org.example;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private final int number;

    public FactorialTask(int n) {
        this.number = n;
    }

    @Override
    protected Long compute() {
        if (number == 1) {
            return 1L;
        }

        FactorialTask factorialTask = new FactorialTask(number - 1);
        factorialTask.fork();

        return number * factorialTask.join();
    }
}
