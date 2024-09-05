package com.crypto.wallet.app.integration;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;

public interface IFindLastDayCryptocurrencySummaryRest {

    Ticker getSummary(DigitalCurrencyAcronym digitalCurrencyAcronym);
}
