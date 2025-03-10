package org.example;

public class BankAccount {

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

    public synchronized void withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " from the balance");
        }
    }

    public synchronized long getBalance() {
        return balance;
    }
}
