package com.crypto.wallet.domain;

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
    private DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private double quantity;
    private Crypto crypto;
    private LocalDateTime date;

    private SalesHistoryDocument(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymDocument digitalCurrencyAcronym, TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptoWallet.getQuantity();
        this.crypto = Crypto.from(operation);
        this.date = LocalDateTime.now();
    }

    public static SalesHistoryDocument of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymDocument digitalCurrencyAcronym, TypeOperation operation) {
        return new SalesHistoryDocument(cryptoWallet, digitalCurrencyAcronym, operation);
    }
}
