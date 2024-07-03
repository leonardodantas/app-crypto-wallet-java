package com.crypto.wallet.infra.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class WalletEntity {

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "digital_currency_acronym_id", referencedColumnName = "id")
    private DigitalCurrencyAcronymEntity digitalCurrencyAcronym;
    private double quantity;

    private WalletEntity(DigitalCurrencyAcronymEntity digitalCurrencyAcronym, ICryptocurrencyWallet cryptoWallet) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptoWallet.getQuatity();
    }

    public static WalletEntity of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymEntity digitalCurrencyAcronym) {
        return new WalletEntity(digitalCurrencyAcronym, cryptoWallet);
    }

    public void overrideWallet(WalletEntity wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
}
