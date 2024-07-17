package com.crypto.wallet.infra.database.entities;

import com.crypto.wallet.domain.Wallet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "wallet")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WalletEntity {

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "digital_currency_acronym_id", referencedColumnName = "id")
    private DigitalCurrencyAcronymEntity digitalCurrencyAcronym;
    private double quantity;

    public String getDigitalCurrencyAcronymName() {
        return this.digitalCurrencyAcronym.getName();
    }

    public String getDigitalCurrencyAcronymDescription() {
        return this.digitalCurrencyAcronym.getDescription();
    }

    public static WalletEntity from(final Wallet wallet) {
        return new WalletEntity(UUID.randomUUID().toString(), DigitalCurrencyAcronymEntity.from(wallet.getDigitalCurrencyAcronym()), wallet.getQuantity());
    }
}
