package com.crypto.wallet.infra.controllers.jsons.responses;

import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class TickerResponse {

    private DigitalCurrencyAcronymResponse digitalCurrencyAcronym;
    private CryptocurrencySummaryResponse ticker;

    private TickerResponse(ITickerDTO ticker, DigitalCurrencyAcronymResponse digitalCurrencyAcronym) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.ticker = CryptocurrencySummaryResponse.from(ticker);
    }

    private TickerResponse(final TickerDocument tickerDocument) {
        this.digitalCurrencyAcronym = DigitalCurrencyAcronymResponse.from(tickerDocument.getDigitalCurrencyAcronym());
        this.ticker = CryptocurrencySummaryResponse.from(tickerDocument.getCryptocurrencySummaryDocument());
    }

    public static TickerResponse of(ITickerDTO ticker, DigitalCurrencyAcronymResponse digitalCurrencyAcronym) {
        return new TickerResponse(ticker, digitalCurrencyAcronym);
    }

    public static TickerResponse from(final TickerDocument tickerDocument){
        return new TickerResponse(tickerDocument);
    }

    public BigDecimal getBuy(){
        return ticker.getBuy();
    }
}
