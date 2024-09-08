package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.Wallet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document("wallet")
public class WalletDocument {

    @Id
    private String id;
    private DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private double quantity;

    private WalletDocument(final Wallet wallet) {
        this.id = wallet.id();
        this.quantity = wallet.quantity();
        this.digitalCurrencyAcronym = DigitalCurrencyAcronymDocument.from(wallet.digitalCurrencyAcronym());
    }

    public static WalletDocument from(final Wallet wallet) {
        return new WalletDocument(wallet);
    }

}
