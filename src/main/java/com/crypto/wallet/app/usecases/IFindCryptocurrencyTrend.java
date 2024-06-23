package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.models.responses.CryptocurrencyTrendResponse;

import java.util.List;

public interface IFindCryptocurrencyTrend {

    List<CryptocurrencyTrendResponse> getByCryptocurrencyName(String cryptocurrency);
}
