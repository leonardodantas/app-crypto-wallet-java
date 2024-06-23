package com.crypto.wallet.infra.database.entities;

import com.crypto.wallet.domain.Crypto;
import com.crypto.wallet.domain.ICryptocurrencyWallet;
import com.crypto.wallet.domain.TypeOperation;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class SalesHistoryEntity {

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "digital_currency_acronym_id", referencedColumnName = "id")
    private DigitalCurrencyAcronymEntity digitalCurrencyAcronymEntity;
    private double quantity;
    @OneToOne(cascade = CascadeType.ALL)
    private Crypto crypto;
    private LocalDateTime date;

    private SalesHistoryEntity(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymEntity digitalCurrencyAcronymEntity, TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronymEntity = digitalCurrencyAcronymEntity;
        this.quantity = cryptoWallet.getQuantity();
        this.crypto = Crypto.from(operation);
        this.date = LocalDateTime.now();
    }

    public static SalesHistoryEntity of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymEntity digitalCurrencyAcronymEntity, TypeOperation operation) {
        return new SalesHistoryEntity(cryptoWallet, digitalCurrencyAcronymEntity, operation);
    }
}
