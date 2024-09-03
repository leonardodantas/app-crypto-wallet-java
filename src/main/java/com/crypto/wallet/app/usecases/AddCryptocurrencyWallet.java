package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.domain.CryptocurrencyWallet;
import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.domain.Cryptocurrency;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCryptocurrencyWallet {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final SaveWallet saveWallet;

    public CryptocurrencyWallet addCryptocurrency(final Cryptocurrency cryptocurrency) {
        final DigitalCurrencyAcronymDocument digitalCurrencyAcronym = this.digitalCurrencyAcronymRepository
                .findByName(cryptocurrency.name()).orElseThrow(() -> new CryptocurrencyNotFoundException(cryptocurrency.name()));

        final WalletDocument wallet = saveWallet.save(cryptocurrency, digitalCurrencyAcronym);

        return CryptocurrencyWallet.of(wallet, digitalCurrencyAcronym);
    }
}
