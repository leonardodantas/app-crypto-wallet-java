package com.crypto.wallet.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@NoArgsConstructor
public class SalesHistory {

    @Id
    private String id;
    private DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private double quantity;
    private Crypto crypto;
    private LocalDateTime date;

    private SalesHistory(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymDocument digitalCurrencyAcronym, TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptoWallet.getQuantity();
        this.crypto = Crypto.from(operation);
        this.date = LocalDateTime.now();
    }

    public static SalesHistory of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymDocument digitalCurrencyAcronym, TypeOperation operation) {
        return new SalesHistory(cryptoWallet, digitalCurrencyAcronym, operation);
    }
}
