package com.crypto.wallet.app.integration;

import com.crypto.wallet.domain.DerivationHistoryPerformed;

import java.util.List;

public interface IFindDerivationHistoryPerformedIntegration {

    List<DerivationHistoryPerformed> getDerivationHistoryPerformed(final String coinName);
}
