package com.crypto.wallet.infra.integration.service;

import com.crypto.wallet.app.integration.IFindDerivationHistoryPerformedIntegration;
import com.crypto.wallet.domain.DerivationHistoryPerformed;
import com.crypto.wallet.infra.integration.feign.DerivationHistoryPerformedFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindDerivationHistoryPerformedIntegrationImpl implements IFindDerivationHistoryPerformedIntegration {

    private final DerivationHistoryPerformedFeign derivationHistoryPerformedFeign;

    @Override
    public List<DerivationHistoryPerformed> getDerivationHistoryPerformed(final String coinName) {
        return derivationHistoryPerformedFeign.getDerivationHistoryPerformed(coinName)
                .stream()
                .map(response -> DerivationHistoryPerformed.of(
                        response.getTid(),
                        response.getAmount(),
                        response.getType(),
                        response.getPrice(),
                        Instant.ofEpochMilli(response.getDate())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()))
                .toList();
    }
}
