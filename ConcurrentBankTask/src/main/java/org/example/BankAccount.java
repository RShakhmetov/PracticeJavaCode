package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private final ReentrantLock lock = new ReentrantLock();
    private long balance;

    public BankAccount(long balance) {
        this.balance = balance;
    }

    public synchronized void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " to the balance");
        }
    }

    public synchronized boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " from the balance");
        }
        return true;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
