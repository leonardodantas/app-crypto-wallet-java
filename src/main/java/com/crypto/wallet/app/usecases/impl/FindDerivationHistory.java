package com.crypto.wallet.app.usecases.impl;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;
import com.crypto.wallet.app.usecases.IFindDerivationHistory;
import com.crypto.wallet.app.rest.IFindDerivationHistoryPerformedRest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindDerivationHistory implements IFindDerivationHistory {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final IFindDerivationHistoryPerformedRest findDerivationHistoryPerformedRest;

    public FindDerivationHistory(IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository, IFindDerivationHistoryPerformedRest findDerivationHistoryPerformedRest) {
        this.digitalCurrencyAcronymRepository = digitalCurrencyAcronymRepository;
        this.findDerivationHistoryPerformedRest = findDerivationHistoryPerformedRest;
    }

    @Override
    public List<DerivationHistoryPerformedResponse> getByCryptocurrencyName(String name) {
        this.digitalCurrencyAcronymRepository
                .findByName(name).orElseThrow(() -> new CryptocurrencyNotFoundException(name));

        return this.findDerivationHistoryPerformedRest.getDerivationHistoryPerformed(name);
    }
}
