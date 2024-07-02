package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCryptocurrencyWallet {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final SaveWallet saveWallet;

    public CryptocurrencyWalletResponse addCryptocurrency(final CryptocurrencyWalletRequest cryptocurrencyWalletRequest) {
        final var digitalCurrencyAcronym = this.digitalCurrencyAcronymRepository
                .findByName(cryptocurrencyWalletRequest.getName()).orElseThrow(() -> new CryptocurrencyNotFoundException(cryptocurrencyWalletRequest.getName()));

        final var wallet = saveWallet.save(cryptocurrencyWalletRequest, digitalCurrencyAcronym);

        return CryptocurrencyWalletResponse.of(wallet, digitalCurrencyAcronym);
    }
}
