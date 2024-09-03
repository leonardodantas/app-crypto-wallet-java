package com.crypto.wallet.domain;

import com.crypto.wallet.infra.controllers.jsons.responses.ITickerDTO;
import com.crypto.wallet.infra.database.mongodb.documents.CryptocurrencySummaryDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor
public class CryptocurrencySummary {

    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal vol;
    private BigDecimal last;
    private BigDecimal buy;
    private BigDecimal sell;
    private BigDecimal open;
    private LocalDateTime date;

    private CryptocurrencySummary(ITickerDTO ticker) {
        this.high = ticker.getHigh();
        this.low = ticker.getLow();
        this.vol = ticker.getVol();
        this.last = ticker.getLast();
        this.buy = ticker.getBuy();
        this.sell = ticker.getSell();
        this.open = ticker.getOpen();
        this.date = LocalDateTime.ofInstant(Instant.ofEpochSecond(ticker.getDate()), ZoneId.of("America/Sao_Paulo"));
    }

    private CryptocurrencySummary(final CryptocurrencySummaryDocument cryptocurrencySummaryDocument) {
        this.high = cryptocurrencySummaryDocument.getHigh();
        this.low = cryptocurrencySummaryDocument.getLow();
        this.vol = cryptocurrencySummaryDocument.getVol();
        this.last = cryptocurrencySummaryDocument.getLast();
        this.buy = cryptocurrencySummaryDocument.getBuy();
        this.sell = cryptocurrencySummaryDocument.getSell();
        this.open = cryptocurrencySummaryDocument.getOpen();
        this.date = cryptocurrencySummaryDocument.getDate();
    }

    public static CryptocurrencySummary from(ITickerDTO ticker) {
        return new CryptocurrencySummary(ticker);
    }

    public static CryptocurrencySummary from(final CryptocurrencySummaryDocument cryptocurrencySummaryDocument) {
        return new CryptocurrencySummary(cryptocurrencySummaryDocument);
    }
}
