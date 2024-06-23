package com.crypto.wallet.app.rest;

import com.crypto.wallet.app.models.responses.TickerResponse;
import com.crypto.wallet.infra.http.responses.DigitalCurrencyAcronymResponse;

public interface IFindLastDayCryptocurrencySummaryRest {

    TickerResponse getSummary(DigitalCurrencyAcronymResponse digitalCurrencyAcronym);
}
