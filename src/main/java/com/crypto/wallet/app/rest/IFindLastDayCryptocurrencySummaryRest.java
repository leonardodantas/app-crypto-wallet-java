package com.crypto.wallet.app.rest;

import com.crypto.wallet.app.models.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.app.models.responses.TickerResponse;

public interface IFindLastDayCryptocurrencySummaryRest {

    TickerResponse getSummary(DigitalCurrencyAcronymResponse digitalCurrencyAcronym);
}
