package com.crypto.wallet.app.rest;

import com.crypto.wallet.infra.controllers.jsons.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.TickerResponse;

public interface IFindLastDayCryptocurrencySummaryRest {

    TickerResponse getSummary(DigitalCurrencyAcronymResponse digitalCurrencyAcronym);
}
