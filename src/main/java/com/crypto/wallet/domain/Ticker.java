package com.crypto.wallet.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Ticker {

    private DigitalCurrencyAcronym digitalCurrencyAcronym;
    private CryptocurrencySummary ticker;

}
