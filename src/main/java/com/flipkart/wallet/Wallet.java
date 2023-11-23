package com.flipkart.wallet;

import java.util.Arrays;

public class Wallet {

    private static final String[] validWalletTypes = { "Personal", "Business" };
    private final String walletType;
    private final String accountHolder;
    private double balanceAmount;

    public Wallet(String walletType, String accountHolder, double balanceAmount) {
        this.walletType = validateWalletType(walletType);
        this.accountHolder = accountHolder;
        this.balanceAmount = balanceAmount;
    }

    private static String validateWalletType(String walletType) {
        boolean isValidWalletType = Arrays.stream(Wallet.validWalletTypes)
                .anyMatch(validWalletType -> validWalletType.equalsIgnoreCase(walletType));

        if (!isValidWalletType)
            throw new IllegalArgumentException("Invalid wallet type");

        return walletType;
    }

    public String getWalletType() {
        return walletType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}
