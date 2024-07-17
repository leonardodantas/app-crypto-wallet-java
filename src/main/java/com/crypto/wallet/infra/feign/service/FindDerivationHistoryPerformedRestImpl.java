package com.crypto.wallet.infra.feign.service;

import com.crypto.wallet.app.client.IFindDerivationHistoryPerformedClient;
import com.crypto.wallet.domain.DerivationHistoryPerformed;
import com.crypto.wallet.infra.feign.converters.DerivationHistoryPerformedConverter;
import com.crypto.wallet.infra.feign.DerivationHistoryPerformedFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindDerivationHistoryPerformedRestImpl implements IFindDerivationHistoryPerformedClient {

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
