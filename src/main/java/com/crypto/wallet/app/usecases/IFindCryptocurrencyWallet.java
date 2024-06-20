package com.crypto.wallet.app.usecases;

import com.crypto.wallet.infra.http.responses.CryptocurrencyWalletResponse;

import java.util.List;

public interface IFindCryptocurrencyWallet {

    CryptocurrencyWalletResponse getByName(String coin);
    List<CryptocurrencyWalletResponse> getAll();
}
