package com.crypto.wallet.infra.database.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sales_history")
@NoArgsConstructor
public class SalesHistoryEntity {

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "digital_currency_acronym_id", referencedColumnName = "id")
    private DigitalCurrencyAcronymEntity digitalCurrencyAcronym;
    private double quantity;
    @OneToOne(cascade = CascadeType.ALL)
    private CryptoEntity crypto;
    private LocalDateTime date;

    private SalesHistoryEntity(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymEntity digitalCurrencyAcronym, TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptoWallet.getQuatity();
        this.crypto = CryptoEntity.from(operation);
        this.date = LocalDateTime.now();
    }

    public static SalesHistoryEntity of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymEntity digitalCurrencyAcronym, TypeOperation operation) {
        return new SalesHistoryEntity(cryptoWallet, digitalCurrencyAcronym, operation);
    }
}
