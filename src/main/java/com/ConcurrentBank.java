package com;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBank {

    private final AtomicLong countOfBankAccount = new AtomicLong(0);
    private final ConcurrentHashMap<Long, BankAccount> idToAccount = new ConcurrentHashMap<>();


    public BankAccount createAccount(BigDecimal amount) {
        long accountNumber = countOfBankAccount.incrementAndGet();
        BankAccount bankAccount = new BankAccount(accountNumber, amount);
        idToAccount.put(accountNumber, bankAccount);
        return bankAccount;
    }

    public void transfer(BankAccount from, BankAccount to, BigDecimal amount) {
        if (!validateData(from, to, amount)) throw new IllegalStateException("Incorrect data");

        Object firstLock = from.getAccountNumber() < to.getAccountNumber() ? from : to;
        Object secondLock = firstLock == from ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance().compareTo(amount) < 0)
                    throw new IllegalStateException("Insufficient funds");
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public synchronized BigDecimal getTotalBalance() {
        BigDecimal totalBalance = BigDecimal.valueOf(0);
        Collection<BankAccount> accounts = idToAccount.values();
        for (BankAccount account : accounts) {
            BigDecimal amount = account.getBalance();
            totalBalance = totalBalance.add(amount);
        }
        return totalBalance;
    }

    public boolean validateData(BankAccount from, BankAccount to, BigDecimal amount) {
        return ((idToAccount.containsKey(from.getAccountNumber()) && idToAccount.containsKey(to.getAccountNumber())) && (amount.compareTo(BigDecimal.ZERO) > 0));
    }

}
