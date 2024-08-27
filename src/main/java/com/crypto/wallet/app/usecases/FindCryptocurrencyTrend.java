package com.crypto.wallet.app.usecases;

import com.crypto.wallet.infra.controllers.jsons.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.infra.controllers.jsons.responses.DerivationHistoryPerformedResponse;
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

    private final FindDerivationHistory getDerivationHistory;
    private final ISimpleRegression simpleRegression;

    public List<CryptocurrencyTrendResponse> getByCryptocurrencyName(final String name) {
        final List<DerivationHistoryPerformedResponse> derivationHistoryPerformed = this.getDerivationHistory.getByCryptocurrencyName(name);

        final List<DataForCalculation> dataForCalculationsBuy = getDataForCalculations(derivationHistoryPerformed, BUY);
        final List<DataForCalculation> dataForCalculationsSell = getDataForCalculations(derivationHistoryPerformed, SELL);

        final BigDecimal buy = simpleRegression.calculeSimpleRegression(dataForCalculationsBuy);
        final BigDecimal sell = simpleRegression.calculeSimpleRegression(dataForCalculationsSell);
        return List.of(CryptocurrencyTrendResponse.of(buy, BUY, name), CryptocurrencyTrendResponse.of(sell, SELL, name));
    }

    private List<DataForCalculation> getDataForCalculations(final List<DerivationHistoryPerformedResponse> derivationHistoryPerformed, String type) {
        return derivationHistoryPerformed.stream()
                .filter(derivationHistoryPerformedDTO -> derivationHistoryPerformedDTO.getType().equals(type))
                .map(DataForCalculation::from)
                .collect(Collectors.toList());
    }


}
