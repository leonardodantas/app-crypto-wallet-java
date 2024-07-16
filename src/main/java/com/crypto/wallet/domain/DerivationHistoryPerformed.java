package com.crypto.wallet.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class DerivationHistoryPerformed {

    private BigDecimal amount;
    private LocalDateTime date;
    private BigDecimal price;
    private long tid;
    private String type;
}
