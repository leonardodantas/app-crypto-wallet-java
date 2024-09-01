package com.crypto.wallet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Document
public class Wallet {

    @Id
    private String id;
    private DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private double quantity;

    private Wallet(DigitalCurrencyAcronymDocument digitalCurrencyAcronym, ICryptocurrencyWallet cryptoWallet) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptoWallet.getQuantity();
    }

    public static Wallet of(ICryptocurrencyWallet cryptoWallet, DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        return new Wallet(digitalCurrencyAcronym, cryptoWallet);
    }

    public void overrideWallet(Wallet wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
}
