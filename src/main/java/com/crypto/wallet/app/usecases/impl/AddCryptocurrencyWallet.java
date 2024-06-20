package com.crypto.wallet.app.usecases.impl;

import com.crypto.wallet.app.exceptions.CryptocurrencyNotFoundException;
import com.crypto.wallet.app.repositories.IDigitalCurrencyAcronymRepository;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCryptocurrencyWallet {

    private final ISalesHistoryRepository salesHistoryRepository;
    private final IWalletRepository walletRepository;
    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;

    public CryptocurrencyWallet addCryptocurrency(final String name, final double quantity) {
        final var digitalCurrencyAcronym = this.digitalCurrencyAcronymRepository
                .findByName(name).orElseThrow(() -> new CryptocurrencyNotFoundException(name));

        final var wallet = saveWallet(digitalCurrencyAcronym, quantity);

        return CryptocurrencyWallet.of(wallet, digitalCurrencyAcronym);
    }

    private Wallet saveWallet(final DigitalCurrencyAcronym digitalCurrencyAcronym, final double quantity) {
        final var salesHistory = SalesHistory.of(digitalCurrencyAcronym, TypeOperation.BUY, quantity);
        salesHistoryRepository.save(salesHistory);

        final var wallet = Wallet.of(digitalCurrencyAcronym, quantity);

        walletRepository
                .findByDigitalCurrencyAcronym(digitalCurrencyAcronym)
                .ifPresent(wallet::overrideWallet);

        return walletRepository.save(wallet);
    }
}
