package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.controllers.jsons.requests.Cryptocurrency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Document("wallet")
public class WalletDocument {

    @Id
    private String id;
    private DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private double quantity;

    private WalletDocument(DigitalCurrencyAcronymDocument digitalCurrencyAcronym, Cryptocurrency cryptocurrency) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptocurrency.quantity();
    }

    public static WalletDocument of(Cryptocurrency cryptocurrency, DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        return new WalletDocument(digitalCurrencyAcronym, cryptocurrency);
    }

    public void overrideWallet(WalletDocument wallet) {
        this.id = wallet.getId();
        this.quantity += wallet.getQuantity();
    }
}
