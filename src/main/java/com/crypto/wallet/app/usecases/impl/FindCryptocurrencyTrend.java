package com.crypto.wallet.app.usecases.impl;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.rest.IFindDerivationHistoryPerformedRest;
import com.crypto.wallet.app.utils.simpleregression.DataForCalculation;
import com.crypto.wallet.app.utils.simpleregression.ISimpleRegression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindCryptocurrencyTrend {

    private static final String BUY = "buy";
    private static final String SELL = "sell";

    private final ISimpleRegression simpleRegression;
    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final IFindDerivationHistoryPerformedRest findDerivationHistoryPerformedRest;

    public List<CryptocurrencyTrendResponse> getByCryptocurrencyName(final String cryptocurrency) {
        final List<DerivationHistoryPerformedResponse> derivationHistoryPerformed = this.getDerivationsHistoriesPerformed(cryptocurrency);

        final List<DataForCalculation> dataForCalculationsBuy = getDataForCalculations(derivationHistoryPerformed, BUY);
        final List<DataForCalculation> dataForCalculationsSell = getDataForCalculations(derivationHistoryPerformed, SELL);

        final BigDecimal buy = simpleRegression.calculeSimpleRegression(dataForCalculationsBuy);
        final BigDecimal sell = simpleRegression.calculeSimpleRegression(dataForCalculationsSell);
        return List.of(CryptocurrencyTrendResponse.of(buy, BUY, cryptocurrency), CryptocurrencyTrendResponse.of(sell, SELL, cryptocurrency));
    }

    private List<DataForCalculation> getDataForCalculations(final List<DerivationHistoryPerformedResponse> derivationHistoryPerformed, final String type) {
        return derivationHistoryPerformed.stream()
                .filter(derivationHistoryPerformedDTO -> derivationHistoryPerformedDTO.getType().equals(type))
                .map(DataForCalculation::from)
                .collect(Collectors.toList());
    }

    private List<DerivationHistoryPerformedResponse> getDerivationsHistoriesPerformed(final String name) {
        this.digitalCurrencyAcronymRepository
                .findByName(name).orElseThrow(() -> new CryptocurrencyNotFoundException(name));

        return this.findDerivationHistoryPerformedRest.getDerivationHistoryPerformed(name);
    }

}
