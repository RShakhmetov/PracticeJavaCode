package org.example;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {

    private final CopyOnWriteArrayList<BankAccount> bankAccounts = new CopyOnWriteArrayList<>();

    public synchronized BankAccount createAccount(long balance) {
        BankAccount account = new BankAccount(balance);
        bankAccounts.add(account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, long amount) {
        ReentrantLock firstLock = from.getLock();
        ReentrantLock secondLock = to.getLock();
        firstLock.lock();
        try {
            secondLock.lock();
            try {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                }
            } finally {
                secondLock.unlock();
            }
        } finally {
            firstLock.unlock();
        }
    }

    public long getTotalBalance() {
        AtomicLong sum = new AtomicLong();
        bankAccounts.forEach(x-> sum.addAndGet(x.getBalance()));
        return sum.get();
    }
}
