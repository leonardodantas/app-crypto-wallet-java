package com.crypto.wallet.infra.controllers.jsons.responses;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;

public record DigitalCurrencyAcronymResponse(
        String name,
        String description
) {

    public static DigitalCurrencyAcronymResponse from(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronymResponse(digitalCurrencyAcronym.name(), digitalCurrencyAcronym.description());
    }
}
