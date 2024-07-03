package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.domain.TypeOperation;
import com.crypto.wallet.domain.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveWallet {

    private final ISalesHistoryRepository salesHistoryRepository;
    private final IWalletRepository walletRepository;

    public Wallet save(final DigitalCurrencyAcronym digitalCurrencyAcronym, final double quantity) {
        final var salesHistory = SalesHistory.of(digitalCurrencyAcronym, TypeOperation.BUY, quantity);
        salesHistoryRepository.save(salesHistory);

        final var wallet = Wallet.of(digitalCurrencyAcronym, quantity);

        walletRepository
                .findByDigitalCurrencyAcronym(digitalCurrencyAcronym)
                .ifPresent(wallet::overrideWallet);

        return walletRepository.save(wallet);
    }
}
