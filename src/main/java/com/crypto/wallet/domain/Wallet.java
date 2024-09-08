package com.crypto.wallet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    private String id;
    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private double quantity;

    public Wallet(final DigitalCurrencyAcronym digitalCurrencyAcronym, final Cryptocurrency cryptocurrency) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptocurrency.quantity();
    }

    private Wallet(final Wallet wallet, final double quantity) {
        this.id = wallet.getId();
        this.digitalCurrencyAcronym = wallet.getDigitalCurrencyAcronym();
        this.quantity = wallet.getQuantity() + quantity;
    }

    public static Wallet of(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new Wallet(digitalCurrencyAcronym, cryptocurrency);
    }

    public static Wallet of(final Wallet wallet, final double quantity) {
        return new Wallet(wallet, quantity);
    }
}
