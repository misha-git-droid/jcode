package com;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class BankAccount {

    private final AtomicReference<BigDecimal> balance;
    private final Long accountNumber;

    public BankAccount(Long accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        balance = new AtomicReference<>(amount);
    }

    public void deposit(BigDecimal amount) {
        balance.updateAndGet(current -> current.add(amount));
    }

    public void withdraw(BigDecimal amount) {
        balance.updateAndGet(current -> current.subtract(amount));
    }

    public BigDecimal getBalance() { return balance.get(); }

    public Long getAccountNumber() {
        return accountNumber;
    }
}
