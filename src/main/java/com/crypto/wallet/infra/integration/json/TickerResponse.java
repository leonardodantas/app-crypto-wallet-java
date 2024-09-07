package com.crypto.wallet.infra.integration.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class TickerResponse {

    private CryptoSummaryRestDTO ticker;

    public BigDecimal getHigh() {
        return ticker.getHigh();
    }

    public BigDecimal getLow() {
        return ticker.getLow();
    }

    public BigDecimal getVol() {
        return ticker.getVol();
    }

    public BigDecimal getLast() {
        return ticker.getLast();
    }

    public BigDecimal getBuy() {
        return ticker.getBuy();
    }

    public BigDecimal getOpen() {
        return ticker.getOpen();
    }

    public BigDecimal getSell() {
        return ticker.getSell();
    }

    public long getDate() {
        return ticker.getDate();
    }
}
