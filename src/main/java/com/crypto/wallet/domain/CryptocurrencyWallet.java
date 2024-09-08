package com.crypto.wallet.domain;

public record CryptocurrencyWallet(
        DigitalCurrencyAcronym digitalCurrencyAcronym,
        double quantity
) {

    public static CryptocurrencyWallet of(final Wallet wallet, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new CryptocurrencyWallet(digitalCurrencyAcronym, wallet.quantity());
    }

    public static CryptocurrencyWallet from(final Wallet wallet) {
        return new CryptocurrencyWallet(wallet.digitalCurrencyAcronym(), wallet.quantity());
    }
}
