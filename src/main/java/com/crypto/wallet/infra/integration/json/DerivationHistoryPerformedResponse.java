package com.crypto.wallet.infra.integration.json;

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
