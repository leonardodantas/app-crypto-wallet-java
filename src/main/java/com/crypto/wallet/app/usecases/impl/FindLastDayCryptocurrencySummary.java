package com.crypto.wallet.app.usecases.impl;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.models.responses.TickerResponse;
import com.crypto.wallet.app.usecases.IFindLastDayCryptocurrencySummary;
import com.crypto.wallet.app.rest.IFindLastDayCryptocurrencySummaryRest;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindLastDayCryptocurrencySummary implements IFindLastDayCryptocurrencySummary {

    private final IFindLastDayCryptocurrencySummaryRest lastDayCoinSummary;
    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;

    public FindLastDayCryptocurrencySummary(IFindLastDayCryptocurrencySummaryRest lastDayCoinSummary, IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository) {
        this.lastDayCoinSummary = lastDayCoinSummary;
        this.digitalCurrencyAcronymRepository = digitalCurrencyAcronymRepository;
    }

    @Override
    public List<TickerResponse> getAllTicker() {
        List<DigitalCurrencyAcronymResponse> digitalCurrencyAcronymResponses = getDigitalCurrencyAcronym();

        return digitalCurrencyAcronymResponses.stream()
                .map(lastDayCoinSummary::getSummary)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<DigitalCurrencyAcronymResponse> getDigitalCurrencyAcronym() {
        List<DigitalCurrencyAcronym> digitalCurrencyAcronymList = digitalCurrencyAcronymRepository.findAll();
        return digitalCurrencyAcronymList.stream().map(DigitalCurrencyAcronymResponse::from).collect(Collectors.toUnmodifiableList());
    }
}
