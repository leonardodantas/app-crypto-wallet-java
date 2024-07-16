package com.crypto.wallet.infra.feign.service;

import com.crypto.wallet.app.rest.IFindDerivationHistoryPerformedRest;
import com.crypto.wallet.domain.DerivationHistoryPerformed;
import com.crypto.wallet.infra.feign.DerivationHistoryPerformedConverter;
import com.crypto.wallet.infra.feign.DerivationHistoryPerformedFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindDerivationHistoryPerformedRestImpl implements IFindDerivationHistoryPerformedRest {

    private final DerivationHistoryPerformedFeign derivationHistoryPerformedFeign;
    private final DerivationHistoryPerformedConverter derivationHistoryPerformedConverter;

    @Override
    public List<DerivationHistoryPerformed> getDerivationHistoryPerformed(final String coinName) {
        return derivationHistoryPerformedFeign.getDerivationHistoryPerformed(coinName)
                .stream()
                .map(derivationHistoryPerformedConverter::convert)
                .toList();
    }
}
