package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.controllers.jsons.responses.TickerResponse;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("ticker")
public class TickerDocument {

    @Id
    private String id;
    private DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private CryptocurrencySummaryDocument cryptocurrencySummaryDocument;

    public TickerDocument(final DigitalCurrencyAcronymDocument digitalCurrencyAcronym, final CryptocurrencySummaryDocument cryptocurrencySummaryDocument) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.cryptocurrencySummaryDocument = cryptocurrencySummaryDocument;
    }

    public static TickerDocument from(final TickerResponse ticker) {
        return new TickerDocument(DigitalCurrencyAcronymDocument.from(ticker.getDigitalCurrencyAcronym()), CryptocurrencySummaryDocument.from(ticker.getTicker()));
    }
}
