package com.crypto.wallet.infra.database.mongodb.documents;

import com.crypto.wallet.domain.CryptocurrencySummary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

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

    public CryptocurrencySummaryDocument(final CryptocurrencySummary ticker) {
        this.high = ticker.getHigh().setScale(2, RoundingMode.HALF_DOWN);
        this.low = ticker.getLow().setScale(2, RoundingMode.HALF_DOWN);
        this.vol = ticker.getVol().setScale(2, RoundingMode.HALF_DOWN);
        this.last = ticker.getLast().setScale(2, RoundingMode.HALF_DOWN);
        this.buy = ticker.getBuy().setScale(2, RoundingMode.HALF_DOWN);
        this.sell = ticker.getSell().setScale(2, RoundingMode.HALF_DOWN);
        this.open = ticker.getOpen().setScale(2, RoundingMode.HALF_DOWN);
        this.date = ticker.getDate();
    }

    public static CryptocurrencySummaryDocument from(final CryptocurrencySummary ticker) {
        return new CryptocurrencySummaryDocument(ticker);
    }
}
