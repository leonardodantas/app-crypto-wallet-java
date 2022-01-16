package com.crypto.wallet.infra.feign.json;

import com.crypto.wallet.app.models.responses.ITickerDTO;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TickerRestDTO implements ITickerDTO {

    private CryptoSummaryRestDTO ticker;

    @Override
    public BigDecimal getHigh() {
        return ticker.getHigh();
    }

    @Override
    public BigDecimal getLow() {
        return ticker.getLow();
    }

    @Override
    public BigDecimal getVol() {
        return ticker.getVol();
    }

    @Override
    public BigDecimal getLast() {
        return ticker.getLast();
    }

    @Override
    public BigDecimal getBuy() {
        return ticker.getBuy();
    }

    @Override
    public BigDecimal getOpen() {
        return ticker.getOpen();
    }

    @Override
    public BigDecimal getSell() {
        return ticker.getSell();
    }

    @Override
    public long getDate() {
        return ticker.getDate();
    }
}
