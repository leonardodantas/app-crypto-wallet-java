package com.crypto.wallet.infra.feign.service;

import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;
import com.crypto.wallet.app.rest.IFindDerivationHistoryPerformedRest;
import com.crypto.wallet.infra.feign.DerivationHistoryPerformedFeign;
import com.crypto.wallet.infra.feign.json.DerivationHistoryPerformedRestDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindDerivationHistoryPerformedRestImpl implements IFindDerivationHistoryPerformedRest {
    
    private final DerivationHistoryPerformedFeign derivationHistoryPerformedFeign;

    public FindDerivationHistoryPerformedRestImpl(DerivationHistoryPerformedFeign derivationHistoryPerformedFeign) {
        this.derivationHistoryPerformedFeign = derivationHistoryPerformedFeign;
    }

    @Override
    public List<DerivationHistoryPerformedResponse> getDerivationHistoryPerformed(String coinName) {
        List<DerivationHistoryPerformedRestDTO> derivationHistoryPerformed = derivationHistoryPerformedFeign.getDerivationHistoryPerformed(coinName);
        return derivationHistoryPerformed.stream().map(DerivationHistoryPerformedResponse::from).collect(Collectors.toUnmodifiableList());
    }
}
