package com.crypto.wallet.app.usecases;

import java.util.List;

public interface IFindCryptocurrencyWallet {

    CryptocurrencyWalletResponse getByName(String coin);
    List<CryptocurrencyWalletResponse> getAll();
}
