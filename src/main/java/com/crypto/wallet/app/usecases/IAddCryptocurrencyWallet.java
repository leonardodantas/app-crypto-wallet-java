package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;

public interface IAddCryptocurrencyWallet {

    CryptocurrencyWalletResponse addCryptocurrency(CryptocurrencyWalletRequest cryptocurrencyWalletRequest);
}
