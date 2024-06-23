package com.crypto.wallet.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class SalesHistory {

    private String id;
    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private double quantity;
    private Crypto crypto;
    private LocalDateTime date;

    private SalesHistory(final DigitalCurrencyAcronym digitalCurrencyAcronym, final TypeOperation operation, final double quantity) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = quantity;
        this.crypto = Crypto.from(operation);
        this.date = LocalDateTime.now();
    }

    public static SalesHistory of(final DigitalCurrencyAcronym digitalCurrencyAcronym, final TypeOperation operation, final double quantity) {
        return new SalesHistory(digitalCurrencyAcronym, operation, quantity);
    }
}
