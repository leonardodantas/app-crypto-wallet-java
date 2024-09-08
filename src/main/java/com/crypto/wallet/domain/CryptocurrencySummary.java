package com.crypto.wallet.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class CryptocurrencySummary {

    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal vol;
    private BigDecimal last;
    private BigDecimal buy;
    private BigDecimal sell;
    private BigDecimal open;
    private LocalDateTime date;

}
