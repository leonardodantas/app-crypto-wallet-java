package com.crypto.wallet.infra.http.responses;

import com.crypto.wallet.domain.CryptocurrencyTrend;

import java.math.BigDecimal;

public record CryptocurrencyTrendResponse(
        String type,
        String cryptocurrency,
        BigDecimal trend

) {

    public static CryptocurrencyTrendResponse from(final CryptocurrencyTrend cryptocurrencyTrend) {
        return new CryptocurrencyTrendResponse(cryptocurrencyTrend.getType(), cryptocurrencyTrend.getCryptocurrency(), cryptocurrencyTrend.getTrend());
    }
}
