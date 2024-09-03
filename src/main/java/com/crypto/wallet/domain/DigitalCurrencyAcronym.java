package com.crypto.wallet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DigitalCurrencyAcronym {

    private String name;
    private String description;

    private DigitalCurrencyAcronym(DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        this.name = digitalCurrencyAcronym.getName();
        this.description = digitalCurrencyAcronym.getDescription();
    }

    public static DigitalCurrencyAcronym from(DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronym(digitalCurrencyAcronym);
    }
}
