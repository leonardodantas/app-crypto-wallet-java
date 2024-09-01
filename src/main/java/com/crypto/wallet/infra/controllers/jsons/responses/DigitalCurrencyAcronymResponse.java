package com.crypto.wallet.infra.controllers.jsons.responses;

import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DigitalCurrencyAcronymResponse {

    private String name;
    private String description;

    private DigitalCurrencyAcronymResponse(DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        this.name = digitalCurrencyAcronym.getName();
        this.description = digitalCurrencyAcronym.getDescription();
    }

    public static DigitalCurrencyAcronymResponse from(DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronymResponse(digitalCurrencyAcronym);
    }
}
