package com.crypto.wallet.domain;

import java.time.LocalDateTime;

public record SalesHistory(
        DigitalCurrencyAcronym digitalCurrencyAcronym,
        double quantity,
        TypeOperation typeOperation,
        LocalDateTime date

) {

    public static SalesHistory of(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym, final TypeOperation typeOperation) {
        return new SalesHistory(digitalCurrencyAcronym, cryptocurrency.quantity(), typeOperation, LocalDateTime.now());
    }
}
