package com.crypto.wallet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesHistory {

    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private double quantity;
    private Crypto crypto;
    private LocalDateTime date;

    private SalesHistory(final DigitalCurrencyAcronym digitalCurrencyAcronym, final TypeOperation operation, final double quantity) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = quantity;
        this.crypto = Crypto.from(operation);
        this.date = LocalDateTime.now();
    }

    public static SalesHistory of(final DigitalCurrencyAcronym digitalCurrencyAcronym, final TypeOperation operation, final double quantity) {
        return new SalesHistory(digitalCurrencyAcronym, operation, quantity);
    }
}
