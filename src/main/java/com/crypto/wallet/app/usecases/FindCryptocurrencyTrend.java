package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.utils.simpleregression.ISimpleRegression;
import com.crypto.wallet.domain.CryptocurrencyTrend;
import com.crypto.wallet.domain.DataForCalculation;
import com.crypto.wallet.domain.DerivationHistoryPerformed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCryptocurrencyTrend {

    private static final String BUY = "buy";
    private static final String SELL = "sell";

    private final FindDerivationHistory getDerivationHistory;
    private final ISimpleRegression simpleRegression;

    public List<CryptocurrencyTrend> getByCryptocurrencyName(final String name) {
        final var derivationHistoryPerformed = this.getDerivationHistory.getByCryptocurrencyName(name);

        final var purchasePrice = getPriceByType(derivationHistoryPerformed, BUY);
        final var salePrice = getPriceByType(derivationHistoryPerformed, SELL);

        return List.of(
                CryptocurrencyTrend.of(purchasePrice, BUY, name),
                CryptocurrencyTrend.of(salePrice, SELL, name)
        );
    }

    private BigDecimal getPriceByType(final List<DerivationHistoryPerformed> derivationHistoryPerformed, final String type) {
        final var dataForCalculations = derivationHistoryPerformed.stream()
                .filter(derivationHistoryPerformedDTO -> derivationHistoryPerformedDTO.getType().equals(type))
                .map(DataForCalculation::from)
                .toList();

        return simpleRegression.calculeSimpleRegression(dataForCalculations);
    }


}
