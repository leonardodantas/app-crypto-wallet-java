package com.crypto.wallet.infra.feign.service;

import com.crypto.wallet.app.rest.IFindLastDayCryptocurrencySummaryRest;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;
import com.crypto.wallet.infra.feign.FindLastDayCryptocurrencySummaryFeign;
import com.crypto.wallet.infra.feign.json.TickerRestDTO;
import org.springframework.stereotype.Component;

@Component
public class FindLastDayCryptocurrencySummaryRestImpl implements IFindLastDayCryptocurrencySummaryRest {

    private final FindLastDayCryptocurrencySummaryFeign findLastDayCryptocurrencySummaryFeign;

    public FindLastDayCryptocurrencySummaryRestImpl(FindLastDayCryptocurrencySummaryFeign findLastDayCryptocurrencySummaryFeign) {
        this.findLastDayCryptocurrencySummaryFeign = findLastDayCryptocurrencySummaryFeign;
    }

    @Override
    public Ticker getSummary(DigitalCurrencyAcronym digitalCurrencyAcronym) {
        TickerRestDTO tickerRest = this.findLastDayCryptocurrencySummaryFeign.getSummary(digitalCurrencyAcronym.getName());
        return Ticker.of(tickerRest, digitalCurrencyAcronym);
    }
}
