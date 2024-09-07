package com.crypto.wallet.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Wallet {

    private String id;
    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private double quantity;

    public Wallet(final DigitalCurrencyAcronym digitalCurrencyAcronym, final Cryptocurrency cryptocurrency) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptocurrency.quantity();
    }

    public static Wallet of(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new Wallet(digitalCurrencyAcronym, cryptocurrency);
    }

    public void overrideWallet(final Wallet wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
}
