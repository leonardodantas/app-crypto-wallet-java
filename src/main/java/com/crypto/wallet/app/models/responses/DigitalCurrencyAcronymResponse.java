package com.crypto.wallet.app.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DigitalCurrencyAcronymResponse {

    private String name;
    private String description;

    private DigitalCurrencyAcronymResponse(com.crypto.wallet.infra.http.responses.DigitalCurrencyAcronymResponse digitalCurrencyAcronymResponse) {
        this.name = digitalCurrencyAcronymResponse.getName();
        this.description = digitalCurrencyAcronymResponse.getDescription();
    }

    public static DigitalCurrencyAcronymResponse from(com.crypto.wallet.infra.http.responses.DigitalCurrencyAcronymResponse digitalCurrencyAcronymResponse) {
        return new DigitalCurrencyAcronymResponse(digitalCurrencyAcronymResponse);
    }
}
