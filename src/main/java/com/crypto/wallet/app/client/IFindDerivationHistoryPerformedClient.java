package com.crypto.wallet.app.client;

import com.crypto.wallet.domain.DerivationHistoryPerformed;

import java.util.List;

public interface IFindDerivationHistoryPerformedClient {

    List<DerivationHistoryPerformed> getDerivationHistoryPerformed(final String coinName);
}
