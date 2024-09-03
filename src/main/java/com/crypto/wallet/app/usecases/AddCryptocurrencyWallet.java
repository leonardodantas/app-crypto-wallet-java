package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.infra.controllers.jsons.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.domain.CryptocurrencyWallet;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCryptocurrencyWallet {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final SaveWallet saveWallet;

    public CryptocurrencyWallet addCryptocurrency(final CryptocurrencyWalletRequest cryptocurrencyWalletRequest) {
        final DigitalCurrencyAcronymDocument digitalCurrencyAcronym = this.digitalCurrencyAcronymRepository
                .findByName(cryptocurrencyWalletRequest.getName()).orElseThrow(() -> new CryptocurrencyNotFoundException(cryptocurrencyWalletRequest.getName()));

        final WalletDocument wallet = saveWallet.save(cryptocurrencyWalletRequest, digitalCurrencyAcronym);

        return CryptocurrencyWallet.of(wallet, digitalCurrencyAcronym);
    }
}
