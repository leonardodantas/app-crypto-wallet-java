package com.crypto.wallet.domain;

public record DigitalCurrencyAcronym(
        String name,
        String description
) {

    public static DigitalCurrencyAcronym of(final String name, final String description) {
        return new DigitalCurrencyAcronym(name, description);
    }
}
