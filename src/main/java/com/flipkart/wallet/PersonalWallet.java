package com.flipkart.wallet;

public class PersonalWallet extends Wallet {
    private static final double minimumAccountBalance = 50.0;
    public PersonalWallet(String accountHolder, double balanceAmount) {
        super("Personal", accountHolder, validateBalanceAmount(balanceAmount));
    }

    private static double validateBalanceAmount(double balanceAmount) {
        if (balanceAmount < minimumAccountBalance)
            throw new IllegalArgumentException("Insufficient balance for personal wallet");

        return balanceAmount;
    }
}
