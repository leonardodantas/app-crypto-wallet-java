package com.crypto.wallet.domain;

import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import lombok.Getter;

@Getter
public class CryptocurrencyWallet {

    private final String id;
    private final DigitalCurrencyAcronym digitalCurrencyAcronym;
    private final double quantity;

    private CryptocurrencyWallet(WalletDocument wallet, DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        this.id = wallet.getId();
        this.digitalCurrencyAcronym = DigitalCurrencyAcronym.from(digitalCurrencyAcronym);
        this.quantity = wallet.getQuantity();
    }

    private CryptocurrencyWallet(WalletDocument wallet) {
        this.id = wallet.getId();
        this.digitalCurrencyAcronym = DigitalCurrencyAcronym.from(wallet.getDigitalCurrencyAcronym());
        this.quantity = wallet.getQuantity();
    }

    public static CryptocurrencyWallet of(WalletDocument wallet, DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        return new CryptocurrencyWallet(wallet, digitalCurrencyAcronym);
    }

    public static CryptocurrencyWallet from(WalletDocument wallet) {
        return new CryptocurrencyWallet(wallet);
    }
}
