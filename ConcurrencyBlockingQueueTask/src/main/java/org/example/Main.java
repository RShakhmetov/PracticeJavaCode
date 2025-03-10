package org.example;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new BlockingQueue<>(5);
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    queue.enqueue(i);
                    System.out.println("Producer: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    int item = queue.dequeue();
                    System.out.println("Consumer: " + item);
                    System.out.println(queue.size());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}