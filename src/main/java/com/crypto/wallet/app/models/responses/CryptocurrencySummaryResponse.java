package com.crypto.wallet.app.models.responses;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class CryptocurrencySummaryResponse {

    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal vol;
    private final BigDecimal last;
    private final BigDecimal buy;
    private final BigDecimal sell;
    private final BigDecimal open;
    private final LocalDateTime date;

    private CryptocurrencySummaryResponse(ITickerDTO ticker) {
        this.high = ticker.getHigh();
        this.low = ticker.getLow();
        this.vol = ticker.getVol();
        this.last = ticker.getLast();
        this.buy = ticker.getBuy();
        this.sell = ticker.getSell();
        this.open = ticker.getOpen();
        this.date = LocalDateTime.ofInstant(Instant.ofEpochSecond(ticker.getDate()), ZoneId.of("America/Sao_Paulo"));
    }

    public static CryptocurrencySummaryResponse from(ITickerDTO ticker) {
        return new CryptocurrencySummaryResponse(ticker);
    }
}
