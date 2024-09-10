package com.crypto.wallet.domain;

public record DigitalCurrencyAcronym(
        String id,
        String name,
        String description
) {

    public static DigitalCurrencyAcronym of(final String id, final String name, final String description) {
        return new DigitalCurrencyAcronym(id, name, description);
    }
}
