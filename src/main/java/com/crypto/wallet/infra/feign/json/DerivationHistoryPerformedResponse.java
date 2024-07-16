package com.crypto.wallet.infra.feign.json;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DerivationHistoryPerformedResponse {

    private BigDecimal amount;
    private long date;
    private BigDecimal price;
    private long tid;
    private String type;
}
