package com.crypto.wallet.app.usecases.impl;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.usecases.ISaveWallet;
import com.crypto.wallet.app.usecases.IAddCryptocurrencyWallet;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import org.springframework.stereotype.Service;

@Service
public class AddCryptocurrencyWallet implements IAddCryptocurrencyWallet {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final ISaveWallet saveWallet;

    public AddCryptocurrencyWallet(IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository, ISaveWallet saveWallet) {
        this.digitalCurrencyAcronymRepository = digitalCurrencyAcronymRepository;
        this.saveWallet = saveWallet;
    }

    @Override
    public CryptocurrencyWalletResponse addCryptocurrency(CryptocurrencyWalletRequest cryptocurrencyWalletRequest) {
        DigitalCurrencyAcronym digitalCurrencyAcronym = this.digitalCurrencyAcronymRepository
                .findByName(cryptocurrencyWalletRequest.getName()).orElseThrow(() -> new CryptocurrencyNotFoundException(cryptocurrencyWalletRequest.getName()));

        Wallet wallet = saveWallet.save(cryptocurrencyWalletRequest, digitalCurrencyAcronym);

        return CryptocurrencyWalletResponse.of(wallet, digitalCurrencyAcronym);
    }
}
