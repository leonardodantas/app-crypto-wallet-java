package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Document("digital_currency_acronym")
public class DigitalCurrencyAcronymDocument {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;

    private DigitalCurrencyAcronymDocument(final String id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static DigitalCurrencyAcronymDocument from(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new DigitalCurrencyAcronymDocument(digitalCurrencyAcronym.id(), digitalCurrencyAcronym.name(), digitalCurrencyAcronym.description());
    }

    public static DigitalCurrencyAcronymDocument from(final Ticker ticker) {
        return new DigitalCurrencyAcronymDocument(UUID.randomUUID().toString(), ticker.digitalCurrencyAcronym().name(), ticker.digitalCurrencyAcronym().description());
    }
}
