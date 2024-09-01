package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.infra.controllers.jsons.responses.CryptocurrencySummaryResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.ITickerDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor
public class CryptocurrencySummaryDocument {

    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal vol;
    private BigDecimal last;
    private BigDecimal buy;
    private BigDecimal sell;
    private BigDecimal open;
    private LocalDateTime date;

    private CryptocurrencySummaryDocument(ITickerDTO ticker) {
        this.high = ticker.getHigh();
        this.low = ticker.getLow();
        this.vol = ticker.getVol();
        this.last = ticker.getLast();
        this.buy = ticker.getBuy();
        this.sell = ticker.getSell();
        this.open = ticker.getOpen();
        this.date = LocalDateTime.ofInstant(Instant.ofEpochSecond(ticker.getDate()), ZoneId.of("America/Sao_Paulo"));
    }

    public CryptocurrencySummaryDocument(final CryptocurrencySummaryResponse ticker) {
        this.high = ticker.getHigh().setScale(2, RoundingMode.HALF_DOWN);
        this.low = ticker.getLow().setScale(2, RoundingMode.HALF_DOWN);
        this.vol = ticker.getVol().setScale(2, RoundingMode.HALF_DOWN);
        this.last = ticker.getLast().setScale(2, RoundingMode.HALF_DOWN);
        this.buy = ticker.getBuy().setScale(2, RoundingMode.HALF_DOWN);
        this.sell = ticker.getSell().setScale(2, RoundingMode.HALF_DOWN);
        this.open = ticker.getOpen().setScale(2, RoundingMode.HALF_DOWN);
        this.date = ticker.getDate();
    }

    public static CryptocurrencySummaryDocument from(ITickerDTO ticker) {
        return new CryptocurrencySummaryDocument(ticker);
    }

    public static CryptocurrencySummaryDocument from(final CryptocurrencySummaryResponse ticker) {
        return new CryptocurrencySummaryDocument(ticker);
    }
}
