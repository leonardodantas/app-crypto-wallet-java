package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.domain.TypeOperation;
import com.crypto.wallet.infra.controllers.jsons.requests.Cryptocurrency;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("sales_history")
@NoArgsConstructor
public class SalesHistoryDocument {

    @Id
    private String id;
    private com.crypto.wallet.domain.DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private double quantity;
    private CryptoDocument crypto;
    private LocalDateTime date;

    private SalesHistoryDocument(Cryptocurrency cryptocurrency, DigitalCurrencyAcronymDocument digitalCurrencyAcronym, TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptocurrency.quantity();
        this.crypto = CryptoDocument.from(operation);
        this.date = LocalDateTime.now();
    }

    public static SalesHistoryDocument of(Cryptocurrency cryptocurrency, DigitalCurrencyAcronymDocument digitalCurrencyAcronym, TypeOperation operation) {
        return new SalesHistoryDocument(cryptocurrency, digitalCurrencyAcronym, operation);
    }
}
