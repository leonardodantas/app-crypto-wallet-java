package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.models.responses.DerivationHistoryPerformedResponse;

import java.util.List;

public interface IFindDerivationHistory {

    List<DerivationHistoryPerformedResponse> getByCryptocurrencyName(String coinName);
}
