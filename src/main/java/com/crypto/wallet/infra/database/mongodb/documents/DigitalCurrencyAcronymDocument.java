package com.crypto.wallet.infra.database.mongodb.documents;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "digital_currency_acronym")
public class DigitalCurrencyAcronymDocument {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;

}
