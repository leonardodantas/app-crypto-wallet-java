package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;
import com.crypto.wallet.app.utils.simpleregression.DataForCalculation;
import com.crypto.wallet.app.utils.simpleregression.ISimpleRegression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindCryptocurrencyTrend {

    private static final String BUY = "buy";
    private static final String SELL = "sell";

    private FindDerivationHistory getDerivationHistory;
    private ISimpleRegression simpleRegression;

    public List<CryptocurrencyTrendResponse> getByCryptocurrencyName(final String name) {
        final var derivationHistoryPerformed = this.getDerivationHistory.getByCryptocurrencyName(name);

        final var dataForCalculationsBuy = getDataForCalculations(derivationHistoryPerformed, BUY);
        final var dataForCalculationsSell = getDataForCalculations(derivationHistoryPerformed, SELL);

        final var buy = simpleRegression.calculeSimpleRegression(dataForCalculationsBuy);
        final var sell = simpleRegression.calculeSimpleRegression(dataForCalculationsSell);

        return List.of(CryptocurrencyTrendResponse.of(buy, BUY, name), CryptocurrencyTrendResponse.of(sell, SELL, name));
    }

    private List<DataForCalculation> getDataForCalculations(final List<DerivationHistoryPerformedResponse> derivationHistoryPerformed, final String type) {
        return derivationHistoryPerformed.stream()
                .filter(derivationHistoryPerformedDTO -> derivationHistoryPerformedDTO.getType().equals(type))
                .map(DataForCalculation::from)
                .collect(Collectors.toList());
    }


}
