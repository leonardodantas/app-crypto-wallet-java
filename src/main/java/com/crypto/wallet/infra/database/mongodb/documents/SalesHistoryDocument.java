package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.domain.TypeOperation;
import com.crypto.wallet.domain.Cryptocurrency;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("sales_history")
@NoArgsConstructor
public class SalesHistoryDocument {

    @Id
    private String id;
    private DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private double quantity;
    private TypeOperation typeOperation;
    private LocalDateTime date;

    private SalesHistoryDocument(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronymDocument digitalCurrencyAcronym, final TypeOperation typeOperation) {
        this.id = UUID.randomUUID().toString();
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.quantity = cryptocurrency.quantity();
        this.typeOperation = typeOperation;
        this.date = LocalDateTime.now();
    }

    private SalesHistoryDocument(final SalesHistory salesHistory) {
        this.digitalCurrencyAcronym = DigitalCurrencyAcronymDocument.from(salesHistory.getDigitalCurrencyAcronym());
        this.quantity = salesHistory.getQuantity();
        this.typeOperation = salesHistory.getTypeOperation();
        this.date = LocalDateTime.now();
    }

    public static SalesHistoryDocument of(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronymDocument digitalCurrencyAcronym, final TypeOperation operation) {
        return new SalesHistoryDocument(cryptocurrency, digitalCurrencyAcronym, operation);
    }

    public static SalesHistoryDocument from(final SalesHistory salesHistory) {
        return new SalesHistoryDocument(salesHistory);
    }
}
