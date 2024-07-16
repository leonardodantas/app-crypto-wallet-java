package com.crypto.wallet.app.rest;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;

public interface IFindLastDayCryptocurrencySummaryRest {

    Ticker getSummary(final DigitalCurrencyAcronym digitalCurrencyAcronym);
}
