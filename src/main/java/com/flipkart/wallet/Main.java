package com.flipkart.wallet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WalletSystem walletSystem = new WalletSystem();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");

            try {
                switch (tokens[0]) {
                    case "CreateWallet":
                        String type = tokens[1];
                        String accountHolder01 = tokens[2];

                        if (type.equalsIgnoreCase("Personal")) {
                            double balanceAmount = Double.parseDouble(tokens[3]);
                            walletSystem.createPersonalWallet(accountHolder01, balanceAmount);
                        } else if (type.equalsIgnoreCase("Business")) {
                            String gst = tokens[3];
                            double balanceAmount = Double.parseDouble(tokens[4]);
                            walletSystem.createBusinessWallet(accountHolder01, gst, balanceAmount);
                        }
                        // TODO: Check for invalid wallet type and show error
                        break;

                    case "AddMoney":
                        String accountHolder02 = tokens[1];
                        double balanceAmount01 = Double.parseDouble(tokens[2]);

                        walletSystem.addMoney(accountHolder02, balanceAmount01);
                        break;

                    case "TransferMoney":
                        String sourceAccount = tokens[1];
                        String destinationAccount = tokens[2];
                        double transferAmount = Double.parseDouble(tokens[3]);

                        walletSystem.transferMoney(sourceAccount, destinationAccount, transferAmount);
                        break;

                    case "Balance":
                        String accountHolder = tokens[1];
                        walletSystem.showBalance(accountHolder);
                        break;

                    case "Overview":
                        walletSystem.showOverview();
                        break;

                    case "Quit":
                        System.out.println("Goodbye : Shutting down wallet system.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Failure : Invalid commend - " + input);
                }
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                System.out.println("Failed : Invalid number of tokens - " + input);
            } catch (Exception exception) {
                System.out.println("Failed : " + exception.getMessage());
            }

        }
    }
}