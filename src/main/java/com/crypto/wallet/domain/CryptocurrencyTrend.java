package com.crypto.wallet.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CryptocurrencyTrend {

    private final String type;
    private final String cryptocurrency;
    private final BigDecimal trend;

    private CryptocurrencyTrend(final BigDecimal trend, final String type, final String cryptocurrency) {
        this.type = type;
        this.trend = trend;
        this.cryptocurrency = cryptocurrency;
    }

    public static CryptocurrencyTrend of(final BigDecimal buy, final String type, final String crypto) {
        return new CryptocurrencyTrend(buy, type, crypto);
    }
}
