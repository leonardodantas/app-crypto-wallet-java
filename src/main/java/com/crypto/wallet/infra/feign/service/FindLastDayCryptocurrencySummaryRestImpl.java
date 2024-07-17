package com.crypto.wallet.infra.feign.service;

import com.crypto.wallet.app.client.IFindLastDayCryptocurrencySummaryClient;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;
import com.crypto.wallet.infra.feign.FindLastDayCryptocurrencySummaryFeign;
import com.crypto.wallet.infra.feign.converters.TickerConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindLastDayCryptocurrencySummaryRestImpl implements IFindLastDayCryptocurrencySummaryClient {

    private final TickerConverter tickerConverter;
    private final FindLastDayCryptocurrencySummaryFeign findLastDayCryptocurrencySummaryFeign;

    @Override
    public Ticker getSummary(final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        final var response = this.findLastDayCryptocurrencySummaryFeign.getSummary(digitalCurrencyAcronym.name());
        return tickerConverter.toDomain(digitalCurrencyAcronym)
                .convert(response);
    }
}
