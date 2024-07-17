package com.crypto.wallet.infra.feign.json;

import java.math.BigDecimal;

public record CryptoSummaryResponse(
        BigDecimal high,
        BigDecimal low,
        BigDecimal vol,
        BigDecimal last,
        BigDecimal buy,
        BigDecimal open,
        BigDecimal sell,
        long date
) {

}
