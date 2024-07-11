package com.crypto.wallet.domain;

import lombok.Getter;

@Getter
public class CryptocurrencyWallet {

    private final String id;
    private final DigitalCurrencyAcronym digitalCurrencyAcronym;
    private final double quantity;

    private CryptocurrencyWallet(final Wallet wallet, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        this.id = wallet.getId();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = wallet.getQuantity();
    }

    private CryptocurrencyWallet(final Wallet wallet) {
        this.id = wallet.getId();
        this.digitalCurrencyAcronym = wallet.getDigitalCurrencyAcronym();
        this.quantity = wallet.getQuantity();
    }

    public static CryptocurrencyWallet of(final Wallet wallet, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new CryptocurrencyWallet(wallet, digitalCurrencyAcronym);
    }

    public static CryptocurrencyWallet from(final Wallet wallet) {
        return new CryptocurrencyWallet(wallet);
    }
}
