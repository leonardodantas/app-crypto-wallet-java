package com.crypto.wallet.app.models.responses;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CryptocurrencyTrendResponse {

    private final String type;
    private final String cryptocurrency;
    private final BigDecimal trend;

    private CryptocurrencyTrendResponse(BigDecimal trend, String type, String cryptocurrency) {
        this.type = type;
        this.trend = trend;
        this.cryptocurrency = cryptocurrency;
    }

    public static CryptocurrencyTrendResponse of(BigDecimal buy, String type, String crypto) {
        return new CryptocurrencyTrendResponse(buy, type, crypto);
    }
}
