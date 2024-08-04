package com.crypto.wallet.domain;

import java.math.BigDecimal;

public record DigitalCurrencyAcronym(
        String name,
        String description,
        BigDecimal value
) {

    public static DigitalCurrencyAcronym of(final String name, final String description) {
        return new DigitalCurrencyAcronym(name, description, null);
    }
}
