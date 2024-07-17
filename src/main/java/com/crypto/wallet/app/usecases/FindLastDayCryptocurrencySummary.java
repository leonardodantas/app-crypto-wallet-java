package com.crypto.wallet.app.usecases;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Ticker;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.client.IFindLastDayCryptocurrencySummaryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindLastDayCryptocurrencySummary {

    private final IFindLastDayCryptocurrencySummaryClient lastDayCoinSummary;
    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;

    public List<Ticker> getAllTicker() {
        final var digitalCurrencyAcronymResponses = getDigitalCurrencyAcronym();

        return digitalCurrencyAcronymResponses.stream()
                .map(lastDayCoinSummary::getSummary)
                .toList();
    }

    private List<DigitalCurrencyAcronym> getDigitalCurrencyAcronym() {
        return digitalCurrencyAcronymRepository.findAll();
    }
}
