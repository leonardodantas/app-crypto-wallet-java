package com.crypto.wallet.app.usecases.impl;

import com.crypto.wallet.app.utils.simpleregression.DataForCalculation;
import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;
import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;
import com.crypto.wallet.app.usecases.IFindDerivationHistory;
import com.crypto.wallet.app.usecases.IFindCryptocurrencyTrend;
import com.crypto.wallet.app.utils.simpleregression.ISimpleRegression;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindCryptocurrencyTrend implements IFindCryptocurrencyTrend {

    private static final String BUY = "buy";
    private static final String SELL = "sell";

    private final IFindDerivationHistory getDerivationHistory;
    private final ISimpleRegression simpleRegression;

    public FindCryptocurrencyTrend(IFindDerivationHistory getDerivationHistory, ISimpleRegression simpleRegression) {
        this.getDerivationHistory = getDerivationHistory;
        this.simpleRegression = simpleRegression;
    }

    @Override
    public List<CryptocurrencyTrendResponse> getByCryptocurrencyName(String name) {
        List<DerivationHistoryPerformedResponse> derivationHistoryPerformed = this.getDerivationHistory.getByCryptocurrencyName(name);

        List<DataForCalculation> dataForCalculationsBuy = getDataForCalculations(derivationHistoryPerformed, BUY);
        List<DataForCalculation> dataForCalculationsSell = getDataForCalculations(derivationHistoryPerformed, SELL);

        BigDecimal buy = simpleRegression.calculeSimpleRegression(dataForCalculationsBuy);
        BigDecimal sell = simpleRegression.calculeSimpleRegression(dataForCalculationsSell);
        return List.of(CryptocurrencyTrendResponse.of(buy, BUY, name), CryptocurrencyTrendResponse.of(sell, SELL, name));
    }

    private List<DataForCalculation> getDataForCalculations(List<DerivationHistoryPerformedResponse> derivationHistoryPerformed, String type) {
        return derivationHistoryPerformed.stream()
                .filter(derivationHistoryPerformedDTO -> derivationHistoryPerformedDTO.getType().equals(type))
                .map(DataForCalculation::from)
                .collect(Collectors.toList());
    }


}
