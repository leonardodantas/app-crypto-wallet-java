package com.crypto.wallet.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Ticker {

    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private CryptocurrencySummary ticker;

    public BigDecimal getBuy() {
        return ticker.getBuy();
    }
}
