package com;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args)  {

        ConcurrentBank concurrentBank = new ConcurrentBank();

        BankAccount account1 = concurrentBank.createAccount(BigDecimal.valueOf(150));
        BankAccount account2 = concurrentBank.createAccount(BigDecimal.valueOf(50));

        Thread transferThread1 = new Thread(() -> concurrentBank.transfer(account1, account2, BigDecimal.valueOf(200)));
        Thread transferThread2 = new Thread(() -> concurrentBank.transfer(account2, account1, BigDecimal.valueOf(100)));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Balance of bank account account1: " + account1.getBalance() + ", account2: "
                + account2.getBalance());
        // Вывод общего баланса
        System.out.println("Total balance: " + concurrentBank.getTotalBalance());

    }
}