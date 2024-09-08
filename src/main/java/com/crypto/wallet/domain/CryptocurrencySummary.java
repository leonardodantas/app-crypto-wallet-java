package com.crypto.wallet.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CryptocurrencySummary(
        BigDecimal high,
        BigDecimal low,
        BigDecimal vol,
        BigDecimal last,
        BigDecimal buy,
        BigDecimal sell,
        BigDecimal open,
        LocalDateTime date
) {

    public static CryptocurrencySummary of(final BigDecimal high, final BigDecimal low, final BigDecimal vol, final BigDecimal last, final BigDecimal buy, final BigDecimal sell, final BigDecimal open, final LocalDateTime date) {
        return new CryptocurrencySummary(high, low, vol, last, buy, sell, open, date);
    }
}
