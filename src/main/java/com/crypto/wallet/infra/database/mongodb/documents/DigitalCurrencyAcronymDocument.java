package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("digital_currency_acronym")
public class DigitalCurrencyAcronymDocument {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;

    public DigitalCurrencyAcronymDocument(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public static DigitalCurrencyAcronymDocument from(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronymDocument(digitalCurrencyAcronym.getName(), digitalCurrencyAcronym.getDescription());
    }
}
