package com.crypto.wallet.domain;

import java.math.BigDecimal;

public record CryptocurrencyTrend(
        String type,
        String cryptocurrency,
        BigDecimal trend

) {
    public static CryptocurrencyTrend of(final BigDecimal trend, final String type, final String cryptocurrency) {
        return new CryptocurrencyTrend(type, cryptocurrency, trend);
    }
}
