package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.models.responses.CryptocurrencyWalletResponse;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCryptocurrencyWallet {

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final SaveWallet saveWallet;

    public CryptocurrencyWalletResponse addCryptocurrency(final CryptocurrencyWalletRequest cryptocurrencyWalletRequest) {
        final DigitalCurrencyAcronym digitalCurrencyAcronym = this.digitalCurrencyAcronymRepository
                .findByName(cryptocurrencyWalletRequest.getName()).orElseThrow(() -> new CryptocurrencyNotFoundException(cryptocurrencyWalletRequest.getName()));

        final Wallet wallet = saveWallet.save(cryptocurrencyWalletRequest, digitalCurrencyAcronym);

        return CryptocurrencyWalletResponse.of(wallet, digitalCurrencyAcronym);
    }
}
