package com.crypto.wallet.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CryptocurrencyTrend {

    private final String type;
    private final String cryptocurrency;
    private final BigDecimal trend;

    private CryptocurrencyTrend(BigDecimal trend, String type, String cryptocurrency) {
        this.type = type;
        this.trend = trend;
        this.cryptocurrency = cryptocurrency;
    }

    public static CryptocurrencyTrend of(BigDecimal buy, String type, String crypto) {
        return new CryptocurrencyTrend(buy, type, crypto);
    }
}
