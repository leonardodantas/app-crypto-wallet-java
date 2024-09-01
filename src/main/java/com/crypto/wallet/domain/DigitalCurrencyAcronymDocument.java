package com.crypto.wallet.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("digital_currency_acronym")
public class DigitalCurrencyAcronym {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;

}
