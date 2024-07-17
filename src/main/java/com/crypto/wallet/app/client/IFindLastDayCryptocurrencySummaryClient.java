package com.crypto.wallet.app.client;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;

public interface IFindLastDayCryptocurrencySummaryClient {

    Ticker getSummary(final DigitalCurrencyAcronym digitalCurrencyAcronym);
}
