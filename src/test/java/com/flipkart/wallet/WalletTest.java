package com.flipkart.wallet;

import static org.junit.Assert.*;
import org.junit.Test;

public class WalletTest {
    @Test
    public void testValidPersonalWalletCreation() {
        Wallet wallet = new Wallet("Personal", "John Doe", 100.0);
        assertEquals("Personal", wallet.getWalletType());
        assertEquals("John Doe", wallet.getAccountHolder());
        assertEquals(100.0, wallet.getBalanceAmount(), 0.01);
    }

    @Test
    public void testValidBusinessWalletCreation() {
        Wallet wallet = new Wallet("Business", "Jane Smith", 500.0);
        assertEquals("Business", wallet.getWalletType());
        assertEquals("Jane Smith", wallet.getAccountHolder());
        assertEquals(500.0, wallet.getBalanceAmount(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidWalletType() {
        new Wallet("InvalidType", "Alice", 200.0);
    }

    @Test
    public void testSetBalanceAmount() {
        Wallet wallet = new Wallet("Personal", "Bob", 300.0);
        wallet.setBalanceAmount(400.0);
        assertEquals(400.0, wallet.getBalanceAmount(), 0.01);
    }
}
