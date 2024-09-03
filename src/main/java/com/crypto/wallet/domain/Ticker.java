package com.crypto.wallet.domain;

import com.crypto.wallet.infra.controllers.jsons.responses.ITickerDTO;
import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class Ticker {

    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private CryptocurrencySummary ticker;

    private Ticker(ITickerDTO ticker, DigitalCurrencyAcronym digitalCurrencyAcronym) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.ticker = CryptocurrencySummary.from(ticker);
    }

    private Ticker(final TickerDocument tickerDocument) {
        this.digitalCurrencyAcronym = DigitalCurrencyAcronym.from(tickerDocument.getDigitalCurrencyAcronym());
        this.ticker = CryptocurrencySummary.from(tickerDocument.getCryptocurrencySummary());
    }

    public static Ticker of(ITickerDTO ticker, DigitalCurrencyAcronym digitalCurrencyAcronym) {
        return new Ticker(ticker, digitalCurrencyAcronym);
    }

    public static Ticker from(final TickerDocument tickerDocument){
        return new Ticker(tickerDocument);
    }

    public BigDecimal getBuy(){
        return ticker.getBuy();
    }
}
