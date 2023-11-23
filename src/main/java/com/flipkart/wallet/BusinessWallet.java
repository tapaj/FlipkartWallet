package com.flipkart.wallet;

public class BusinessWallet extends Wallet {
    private String gst;

    public BusinessWallet(String accountHolder, String gst, double balanceAmount) {
        super("Business", accountHolder, balanceAmount);
        this.gst = gst;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }
}
