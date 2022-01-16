package com.crypto.wallet.app.rest;

import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;

import java.util.List;

public interface IFindDerivationHistoryPerformedRest {

    List<DerivationHistoryPerformedResponse> getDerivationHistoryPerformed(String coinName);
}
