package com.crypto.wallet.app.usecases;

import com.crypto.wallet.infra.controllers.jsons.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.TickerResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.rest.IFindLastDayCryptocurrencySummaryRest;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindLastDayCryptocurrencySummary {

    private final IFindLastDayCryptocurrencySummaryRest lastDayCoinSummary;
    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;

    public List<TickerResponse> getAllTicker() {
        final List<DigitalCurrencyAcronymResponse> digitalCurrencyAcronymResponses = getDigitalCurrencyAcronym();

        return digitalCurrencyAcronymResponses.stream()
                .map(lastDayCoinSummary::getSummary)
                .toList();
    }

    private List<DigitalCurrencyAcronymResponse> getDigitalCurrencyAcronym() {
        final List<DigitalCurrencyAcronym> digitalCurrencyAcronymList = digitalCurrencyAcronymRepository.findAll();
        return digitalCurrencyAcronymList.stream().map(DigitalCurrencyAcronymResponse::from).collect(Collectors.toUnmodifiableList());
    }
}
