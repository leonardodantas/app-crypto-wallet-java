package com.crypto.wallet.domain;

import java.math.BigDecimal;

public record Ticker(
        DigitalCurrencyAcronym digitalCurrencyAcronym,
        CryptocurrencySummary ticker
) {

    public BigDecimal getBuy() {
        return ticker.getBuy();
    }
}
