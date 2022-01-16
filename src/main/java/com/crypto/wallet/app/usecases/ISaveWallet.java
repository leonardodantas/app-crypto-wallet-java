package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;

public interface ISaveWallet {

    Wallet save(CryptocurrencyWalletRequest cryptocurrencyWalletRequest, DigitalCurrencyAcronym digitalCurrencyAcronym);

}
