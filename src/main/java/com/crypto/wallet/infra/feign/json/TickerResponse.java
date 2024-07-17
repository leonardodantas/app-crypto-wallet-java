package com.crypto.wallet.infra.feign.json;

import java.math.BigDecimal;

public record TickerResponse(
        CryptoSummaryResponse ticker
) {

    public BigDecimal getHigh() {
        return ticker.high();
    }

    public BigDecimal getLow() {
        return ticker.low();
    }

    public BigDecimal getVol() {
        return ticker.vol();
    }

    public BigDecimal getLast() {
        return ticker.last();
    }

    public BigDecimal getBuy() {
        return ticker.buy();
    }

    public BigDecimal getOpen() {
        return ticker.open();
    }

    public BigDecimal getSell() {
        return ticker.sell();
    }

    public long getDate() {
        return ticker.date();
    }
}
