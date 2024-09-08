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
        this.high = ticker.high().setScale(2, RoundingMode.HALF_DOWN);
        this.low = ticker.low().setScale(2, RoundingMode.HALF_DOWN);
        this.vol = ticker.vol().setScale(2, RoundingMode.HALF_DOWN);
        this.last = ticker.last().setScale(2, RoundingMode.HALF_DOWN);
        this.buy = ticker.buy().setScale(2, RoundingMode.HALF_DOWN);
        this.sell = ticker.sell().setScale(2, RoundingMode.HALF_DOWN);
        this.open = ticker.open().setScale(2, RoundingMode.HALF_DOWN);
        this.date = ticker.date();
    }

    public static CryptocurrencySummaryDocument from(final CryptocurrencySummary ticker) {
        return new CryptocurrencySummaryDocument(ticker);
    }
}
