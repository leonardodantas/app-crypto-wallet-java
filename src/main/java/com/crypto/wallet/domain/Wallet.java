package com.crypto.wallet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Wallet {

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "digital_currency_acronym_id", referencedColumnName = "id")
    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private double quantity;

    private Wallet(DigitalCurrencyAcronym digitalCurrencyAcronym, ICryptocurrencyWallet cryptoWallet) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptoWallet.getQuatity();
    }

    public static Wallet of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new Wallet(digitalCurrencyAcronym, cryptoWallet);
    }

    public void overrideWallet(Wallet wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
}
