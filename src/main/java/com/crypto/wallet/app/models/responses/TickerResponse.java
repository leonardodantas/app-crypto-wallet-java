package com.crypto.wallet.app.models.responses;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TickerResponse {

    private final DigitalCurrencyAcronymResponse digitalCurrencyAcronym;
    private final CryptocurrencySummaryResponse ticker;

    private TickerResponse(ITickerDTO ticker, DigitalCurrencyAcronymResponse digitalCurrencyAcronym) {
        this.digitalCurrencyAcronym = digitalCurrencyAcronym;
        this.ticker = CryptocurrencySummaryResponse.from(ticker);
    }

    public static TickerResponse of(ITickerDTO ticker, DigitalCurrencyAcronymResponse digitalCurrencyAcronym) {
        return new TickerResponse(ticker, digitalCurrencyAcronym);
    }

    public BigDecimal getBuy(){
        return ticker.getBuy();
    }
}
