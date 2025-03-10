package org.example;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {

    private final List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    private BankAccount account;

    public BankAccount createAccount(long balance) {
        account = new BankAccount(balance);
        bankAccounts.add(account);
        return account;
    }

    public synchronized void transfer(BankAccount from, BankAccount to, long amount) {
        if (from.getBalance() >= amount) {
            from.withdraw(amount);
            to.deposit(amount);
        }
    }

    public long getTotalBalance() {
        long sum = 0;
        for (BankAccount bankAccount : bankAccounts) {
            sum += bankAccount.getBalance();
        }
        return sum;
    }
}
