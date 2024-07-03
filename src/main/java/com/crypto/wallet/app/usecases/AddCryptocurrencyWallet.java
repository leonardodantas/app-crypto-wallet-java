package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCryptocurrencyWallet {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final SaveWallet saveWallet;

    public CryptocurrencyWalletResponse addCryptocurrency(final String name, final double quantity) {
        final var digitalCurrencyAcronym = this.digitalCurrencyAcronymRepository
                .findByName(name).orElseThrow(() -> new CryptocurrencyNotFoundException(name));

        final var wallet = saveWallet.save(digitalCurrencyAcronym, quantity);

        return CryptocurrencyWalletResponse.of(wallet, digitalCurrencyAcronym);
    }
}
