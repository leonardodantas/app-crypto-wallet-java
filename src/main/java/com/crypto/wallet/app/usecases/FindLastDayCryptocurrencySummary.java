package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.rest.IFindLastDayCryptocurrencySummaryRest;
import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.controllers.jsons.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.TickerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        final List<DigitalCurrencyAcronymDocument> digitalCurrencyAcronymList = digitalCurrencyAcronymRepository.findAll();
        return digitalCurrencyAcronymList.stream().map(DigitalCurrencyAcronymResponse::from).toList();
    }
}
