package com.crypto.wallet.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SalesHistory {
    private String id;
    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private double quantity;
    private TypeOperation typeOperation;
    private LocalDateTime date;

    private SalesHistory(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym, final TypeOperation typeOperation) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptocurrency.quantity();
        this.typeOperation = typeOperation;
        this.date = LocalDateTime.now();
    }

    public static SalesHistory of(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym, final TypeOperation typeOperation) {
        return new SalesHistory(cryptocurrency, digitalCurrencyAcronym, typeOperation);
    }
}
