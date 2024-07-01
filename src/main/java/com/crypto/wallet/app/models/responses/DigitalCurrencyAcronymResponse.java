package com.crypto.wallet.app.models.responses;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DigitalCurrencyAcronymResponse {

    private String name;
    private String description;

    private DigitalCurrencyAcronymResponse(DigitalCurrencyAcronym digitalCurrencyAcronym) {
        this.name = digitalCurrencyAcronym.getName();
        this.description = digitalCurrencyAcronym.getDescription();
    }

    public static DigitalCurrencyAcronymResponse from(DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronymResponse(digitalCurrencyAcronym);
    }
}
