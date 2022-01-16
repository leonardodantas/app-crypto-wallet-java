package com.crypto.wallet.app.models.responses;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class DerivationHistoryPerformedResponse {

    private final BigDecimal amount;
    private final LocalDateTime date;
    private final BigDecimal price;
    private final long tid;
    private final String type;

    private DerivationHistoryPerformedResponse(IDerivationHistoryPerformed derivationHistoryPerformed) {
        this.amount = derivationHistoryPerformed.getAmount();
        this.date = LocalDateTime.ofInstant(Instant.ofEpochSecond(derivationHistoryPerformed.getDate()), ZoneId.of("America/Sao_Paulo"));
        this.price = derivationHistoryPerformed.getPrice();
        this.tid = derivationHistoryPerformed.getTid();
        this.type = derivationHistoryPerformed.getType();
    }

    public static DerivationHistoryPerformedResponse from(IDerivationHistoryPerformed derivationHistoryPerformed) {
        return new DerivationHistoryPerformedResponse(derivationHistoryPerformed);
    }
}
