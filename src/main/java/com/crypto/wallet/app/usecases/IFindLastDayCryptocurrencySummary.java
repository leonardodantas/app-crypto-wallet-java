package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.models.responses.TickerResponse;

import java.util.List;

public interface IFindLastDayCryptocurrencySummary {

    List<TickerResponse> getAllTicker();
}
