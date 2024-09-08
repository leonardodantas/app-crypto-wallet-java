package com.crypto.wallet.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DerivationHistoryPerformed(
        BigDecimal amount,
        LocalDateTime date,
        BigDecimal price,
        long tid,
        String type
) {

    public static DerivationHistoryPerformed of(final long tid, final BigDecimal amount, final String type, final BigDecimal price, final LocalDateTime date) {
        return new DerivationHistoryPerformed(amount, date, price, tid, type);
    }
}
