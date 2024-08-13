package com.crypto.wallet.app.models.responses;

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

    public static TickerResponse of(ITickerDTO ticker, DigitalCurrencyAcronymResponse digitalCurrencyAcronym) {
        return new TickerResponse(ticker, digitalCurrencyAcronym);
    }

    public BigDecimal getBuy(){
        return ticker.getBuy();
    }
}
