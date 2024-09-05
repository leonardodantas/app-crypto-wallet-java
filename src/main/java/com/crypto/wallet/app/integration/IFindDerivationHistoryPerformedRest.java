package com.crypto.wallet.app.integration;

import com.crypto.wallet.infra.controllers.jsons.responses.DerivationHistoryPerformedResponse;

import java.util.List;

public interface IFindDerivationHistoryPerformedRest {

    List<DerivationHistoryPerformedResponse> getDerivationHistoryPerformed(String coinName);
}
