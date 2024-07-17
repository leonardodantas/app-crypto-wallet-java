package com.crypto.wallet.infra.feign.json;

import java.math.BigDecimal;

public record DerivationHistoryPerformedResponse(
        BigDecimal amount,
        long date,
        BigDecimal price,
        long tid,
        String type
) {

}
