package com.crypto.wallet.infra.integration.json;

import com.crypto.wallet.infra.controllers.jsons.responses.IDerivationHistoryPerformed;

import java.math.BigDecimal;

public class DerivationHistoryPerformedRestDTO implements IDerivationHistoryPerformed {

    private BigDecimal amount;
    private long date;
    private BigDecimal price;
    private long tid;
    private String type;

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public long getDate() {
        return this.date;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public long getTid() {
        return this.tid;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
