package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {

    private ComplexTask complexTask;

    public ComplexTaskExecutor(int numberOfTasks) {
    }

    public void executeTasks(int parties) {
        ExecutorService executor = Executors.newFixedThreadPool(parties);
        List<ComplexTask> tasks = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(parties, () -> {
            System.out.println("Summing results of " + parties + " tasks");
            int result = 0;
            for (ComplexTask task : tasks) {
                result += task.getResult();
            }
            System.out.println("Summing results of " + parties + " tasks with result " + result);
        });

        for (int i = 0; i < parties; i++) {
            ComplexTask task = new ComplexTask( i + 1);
            tasks.add(task);
            executor.submit(() -> {
                task.run();
                try {
                    barrier.await();
                } catch (BrokenBarrierException | InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();
        try {
            if(!executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
