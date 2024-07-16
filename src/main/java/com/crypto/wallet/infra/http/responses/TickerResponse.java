package com.crypto.wallet.infra.http.responses;

import com.crypto.wallet.domain.Ticker;

public record TickerResponse(
        DigitalCurrencyAcronymResponse digitalCurrencyAcronym,
        CryptocurrencySummaryResponse ticker
) {


    public static TickerResponse from(final Ticker ticker) {
        return new TickerResponse(DigitalCurrencyAcronymResponse.from(ticker.getDigitalCurrencyAcronym()), CryptocurrencySummaryResponse.from(ticker.getTicker()));
    }
}
