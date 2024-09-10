package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.domain.DerivationHistoryPerformed;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.integration.IFindDerivationHistoryPerformedIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindDerivationHistory {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final IFindDerivationHistoryPerformedIntegration findDerivationHistoryPerformedRest;

    public List<DerivationHistoryPerformed> getByCryptocurrencyName(final String name) {
        this.digitalCurrencyAcronymRepository
                .findByName(name.toUpperCase()).orElseThrow(() -> new CryptocurrencyNotFoundException(name));

        return findDerivationHistoryPerformedRest.getDerivationHistoryPerformed(name);
    }
}
