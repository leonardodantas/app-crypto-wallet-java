package com.crypto.wallet.app.usecases;

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

    private final IDigitalCurrencyAcronymRepository digitalCurrencyAcronymRepository;
    private final ISalesHistoryRepository salesHistoryRepository;
    private final IWalletRepository walletRepository;

    public CryptocurrencyWallet addCryptocurrency(final Cryptocurrency cryptocurrency) {
        final var digitalCurrencyAcronym = getDigitalCurrencyAcronym(cryptocurrency);

        final var wallet = saveWallet(cryptocurrency, digitalCurrencyAcronym);

        return CryptocurrencyWallet.of(wallet, digitalCurrencyAcronym);
    }

    private DigitalCurrencyAcronym getDigitalCurrencyAcronym(final Cryptocurrency cryptocurrency) {
        return this.digitalCurrencyAcronymRepository
                .findByName(cryptocurrency.name())
                .orElseThrow(() -> new CryptocurrencyNotFoundException(cryptocurrency.name()));
    }

    private Wallet saveWallet(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        saveSalesHistory(cryptocurrency, digitalCurrencyAcronym);

        final var wallet = Wallet.of(cryptocurrency, digitalCurrencyAcronym);

        return saveOrUpdateWallet(cryptocurrency, digitalCurrencyAcronym, wallet);
    }

    private Wallet saveOrUpdateWallet(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym, final Wallet wallet) {
        return walletRepository
                .findByDigitalCurrencyAcronym(digitalCurrencyAcronym)
                .map(walletExist -> {
                    final var walletToUpdate = Wallet.of(walletExist, cryptocurrency.quantity());
                    return walletRepository.save(walletToUpdate);
                }).orElseGet(() -> walletRepository.save(wallet));
    }

    private void saveSalesHistory(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        final var salesHistory = SalesHistory.of(cryptocurrency, digitalCurrencyAcronym, TypeOperation.BUY);
        salesHistoryRepository.save(salesHistory);
    }
}
