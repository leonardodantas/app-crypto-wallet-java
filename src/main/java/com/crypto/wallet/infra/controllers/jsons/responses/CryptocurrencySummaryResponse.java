package com.crypto.wallet.infra.controllers.jsons.responses;

import com.crypto.wallet.domain.CryptocurrencySummary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CryptocurrencySummaryResponse(
        BigDecimal high,
        BigDecimal low,
        BigDecimal vol,
        BigDecimal last,
        BigDecimal buy,
        BigDecimal sell,
        BigDecimal open,
        LocalDateTime date
) {
    public static CryptocurrencySummaryResponse from(final CryptocurrencySummary cryptocurrencySummary) {
        return new CryptocurrencySummaryResponse(
                cryptocurrencySummary.high(),
                cryptocurrencySummary.low(),
                cryptocurrencySummary.vol(),
                cryptocurrencySummary.last(),
                cryptocurrencySummary.buy(),
                cryptocurrencySummary.sell(),
                cryptocurrencySummary.open(),
                cryptocurrencySummary.date()
        );
    }
}
