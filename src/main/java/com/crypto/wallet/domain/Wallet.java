package com.crypto.wallet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Wallet {

    private String id;
    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private double quantity;

    private Wallet(final DigitalCurrencyAcronym digitalCurrencyAcronym, final double quantity) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = quantity;
    }

    public static Wallet of(final DigitalCurrencyAcronym digitalCurrencyAcronym, final double quantity) {
        return new Wallet(digitalCurrencyAcronym, quantity);
    }

    public void overrideWallet(final Wallet wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
}
