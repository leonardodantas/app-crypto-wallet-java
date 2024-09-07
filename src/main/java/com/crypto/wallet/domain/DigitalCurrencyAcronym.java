package com.crypto.wallet.domain;

import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static DigitalCurrencyAcronym of(final String name, final String description) {
        return new DigitalCurrencyAcronym(name, description);
    }
}
