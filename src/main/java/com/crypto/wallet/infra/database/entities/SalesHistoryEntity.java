package com.crypto.wallet.infra.database.entities;

import com.crypto.wallet.domain.SalesHistory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "sales_history")
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

    private SalesHistoryEntity(final SalesHistory salesHistory) {
        this.digitalCurrencyAcronym = DigitalCurrencyAcronymEntity.from(salesHistory.getDigitalCurrencyAcronymResponse());
        this.quantity = salesHistory.getQuantity();
        this.crypto = CryptoEntity.from(salesHistory.getCrypto());
        this.date = salesHistory.getDate();
    }

    public static SalesHistoryEntity from(final SalesHistory salesHistory){
        return new SalesHistoryEntity(salesHistory);
    }
}
