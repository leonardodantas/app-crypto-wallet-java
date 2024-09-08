package com.crypto.wallet.domain;

import java.math.BigDecimal;

public record Ticker(
        DigitalCurrencyAcronym digitalCurrencyAcronym,
        CryptocurrencySummary ticker
) {

    public static Ticker of(final DigitalCurrencyAcronym digitalCurrencyAcronym,
                            final CryptocurrencySummary ticker) {
        return new Ticker(digitalCurrencyAcronym, ticker);
    }

    public BigDecimal getBuy() {
        return ticker.buy();
    }
}
