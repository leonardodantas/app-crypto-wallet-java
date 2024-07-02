package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.models.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.app.models.responses.TickerResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.rest.IFindLastDayCryptocurrencySummaryRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindLastDayCryptocurrencySummary {

    private final IFindLastDayCryptocurrencySummaryRest lastDayCoinSummary;
    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;

    public List<TickerResponse> getAllTicker() {
        final var digitalCurrencyAcronymResponses = getDigitalCurrencyAcronym();

        return digitalCurrencyAcronymResponses.stream()
                .map(lastDayCoinSummary::getSummary)
                .toList();
    }

    private List<DigitalCurrencyAcronymResponse> getDigitalCurrencyAcronym() {
        return digitalCurrencyAcronymRepository
                .findAll()
                .stream()
                .map(DigitalCurrencyAcronymResponse::from)
                .toList();
    }
}
