package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.domain.Ticker;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("ticker")
public class TickerDocument {

    @Id
    private String id;
    private DigitalCurrencyAcronymDocument digitalCurrencyAcronym;
    private CryptocurrencySummaryDocument cryptocurrencySummary;

    public TickerDocument(final DigitalCurrencyAcronymDocument digitalCurrencyAcronym, final CryptocurrencySummaryDocument cryptocurrencySummary) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.cryptocurrencySummary = cryptocurrencySummary;
    }

    public static TickerDocument from(final Ticker ticker) {
        return new TickerDocument(DigitalCurrencyAcronymDocument.from(ticker.getDigitalCurrencyAcronym()), CryptocurrencySummaryDocument.from(ticker.getTicker()));
    }

    public String getName(){
        return digitalCurrencyAcronym.getName();
    }
}
