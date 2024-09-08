package com.crypto.wallet.domain;

public record Wallet(
        String id,
        DigitalCurrencyAcronym digitalCurrencyAcronym,
        double quantity
) {


    public static Wallet of(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new Wallet(null, digitalCurrencyAcronym, cryptocurrency.quantity());
    }

    public static Wallet of(final Wallet wallet, final double quantity) {
        return new Wallet(wallet.id(), wallet.digitalCurrencyAcronym(), wallet.quantity() + quantity);
    }

    public static Wallet of(final String id, final DigitalCurrencyAcronym digitalCurrencyAcronym, final double quantity) {
        return new Wallet(id, digitalCurrencyAcronym, quantity);
    }
}
