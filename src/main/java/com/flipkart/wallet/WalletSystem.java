package com.flipkart.wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletSystem {

    private final List<Wallet> wallets;

    public WalletSystem() {
        wallets = new ArrayList<>();
    }

    public void createPersonalWallet(String accountHolder, double balanceAmount) throws IllegalArgumentException {
        if (checkDuplicateAccountHolder(accountHolder))
            throw new IllegalArgumentException("com.flipkart.wallet.Wallet with same account holder already exists");

        wallets.add(new PersonalWallet(accountHolder, balanceAmount));
    }

    public void createBusinessWallet(String accountHolder, String gst, double balanceAmount) throws IllegalArgumentException {
        if (checkDuplicateAccountHolder(accountHolder))
            throw new IllegalArgumentException("com.flipkart.wallet.Wallet with same account holder already exists");

        wallets.add(new BusinessWallet(accountHolder, gst, balanceAmount));
    }

    public void addMoney(String accountHolder, double balanceAmount) {
        Wallet wallet = getWalletByAccountHolder(accountHolder);

        if (wallet == null) {
            System.out.println("Failed : No wallet found for account holder " + accountHolder);
            return;
        }

        wallet.setBalanceAmount(wallet.getBalanceAmount() + balanceAmount);
    }

    public void showBalance(String accountHolder) {
        Wallet wallet = getWalletByAccountHolder(accountHolder);

        if (wallet == null) {
            System.out.println("Failed : No wallet found for account holder " + accountHolder);
            return;
        }

        System.out.println(wallet.getAccountHolder() + " " + wallet.getBalanceAmount());
    }

    public void transferMoney(String sourceAccountHolder, String destinationAccountHolder, double amount) {
        Wallet sourceWallet = getWalletByAccountHolder(sourceAccountHolder);
        Wallet destinationWallet = getWalletByAccountHolder(destinationAccountHolder);

        if (sourceWallet == null || destinationWallet == null) {
            System.out.println("Failed : No wallet found for either source or destination account");
            return;
        }

        // Rules check:

        // Rule 01: Business wallet can't transfer to personal wallet
        if (sourceWallet.getWalletType().equalsIgnoreCase("Business") && destinationWallet.getWalletType().equalsIgnoreCase("Personal")) {
            System.out.println("Failed : Business to personal wallet transfer not allowed");
            return;
        }

        // Rule 03: Negative balance check
        if (sourceWallet.getBalanceAmount() - amount < 0) {
            System.out.println("Failed : Insufficient balance");
            return;
        }

        // Rule 02: Personal account must maintain a balance of 50
        if (sourceWallet.getWalletType().equalsIgnoreCase("Personal") && sourceWallet.getBalanceAmount() - amount < 50) {
            System.out.println("Failed : Personal account must maintain balance of 50");
            return;
        }

        // Performing the transfer
        sourceWallet.setBalanceAmount(sourceWallet.getBalanceAmount() - amount);
        destinationWallet.setBalanceAmount(destinationWallet.getBalanceAmount() + amount);

        // Bonus reward check
        if (sourceWallet.getBalanceAmount() == destinationWallet.getBalanceAmount()) {
            System.out.println("Rewards earned");
            sourceWallet.setBalanceAmount(sourceWallet.getBalanceAmount() + 10);
            destinationWallet.setBalanceAmount(destinationWallet.getBalanceAmount() + 10);
        }
    }

    public void showOverview() {
        for (Wallet wallet: this.wallets) {
            System.out.println(wallet.getAccountHolder() + " " + wallet.getBalanceAmount());
        }
    }

    private boolean checkDuplicateAccountHolder(String accountHolder) {
        return this.wallets.stream().anyMatch(personalWallet -> personalWallet.getAccountHolder().equalsIgnoreCase(accountHolder));
    }

    private Wallet getWalletByAccountHolder(String accountHolder) {
        return this.wallets.stream()
                .filter(wallet -> wallet.getAccountHolder().equalsIgnoreCase(accountHolder))
                .findFirst()
                .orElse(null);
    }
}
