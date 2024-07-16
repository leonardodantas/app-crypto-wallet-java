package com.crypto.wallet.infra.http.responses;

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
        return new CryptocurrencySummaryResponse(cryptocurrencySummary.getHigh(), cryptocurrencySummary.getLow(), cryptocurrencySummary.getVol(), cryptocurrencySummary.getLast(),
                cryptocurrencySummary.getBuy(), cryptocurrencySummary.getSell(), cryptocurrencySummary.getOpen(), cryptocurrencySummary.getDate());
    }
}
