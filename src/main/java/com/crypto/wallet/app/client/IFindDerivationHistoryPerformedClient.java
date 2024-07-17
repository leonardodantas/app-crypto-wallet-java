package com.crypto.wallet.app.rest;

import com.crypto.wallet.domain.DerivationHistoryPerformed;

import java.util.List;

public interface IFindDerivationHistoryPerformedRest {

    List<DerivationHistoryPerformed> getDerivationHistoryPerformed(final String coinName);
}
