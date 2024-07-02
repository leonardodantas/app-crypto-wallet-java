package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.rest.IFindDerivationHistoryPerformedRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindDerivationHistory {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final IFindDerivationHistoryPerformedRest findDerivationHistoryPerformedRest;

    public List<DerivationHistoryPerformedResponse> getByCryptocurrencyName(final String name) {
        this.digitalCurrencyAcronymRepository
                .findByName(name).orElseThrow(() -> new CryptocurrencyNotFoundException(name));

        return this.findDerivationHistoryPerformedRest.getDerivationHistoryPerformed(name);
    }
}
