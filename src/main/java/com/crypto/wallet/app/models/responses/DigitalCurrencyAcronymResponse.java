package com.crypto.wallet.app.models.responses;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import lombok.Getter;

@Getter
public class DigitalCurrencyAcronymResponse {

    private final String name;
    private final String description;

    private DigitalCurrencyAcronymResponse(DigitalCurrencyAcronym digitalCurrencyAcronym) {
        this.name = digitalCurrencyAcronym.getName();
        this.description = digitalCurrencyAcronym.getDescription();
    }

    public static DigitalCurrencyAcronymResponse from(DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronymResponse(digitalCurrencyAcronym);
    }
}
