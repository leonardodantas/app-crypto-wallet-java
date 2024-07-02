package com.crypto.wallet.app.usecases;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
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

    public Wallet save(final CryptocurrencyWalletRequest cryptocurrencyWalletRequest, final DigitalCurrencyAcronym digitalCurrencyAcronym) {
        final var salesHistory = SalesHistory.of(cryptocurrencyWalletRequest, digitalCurrencyAcronym, TypeOperation.BUY);
        salesHistoryRepository.save(salesHistory);

        final var wallet = Wallet.of(cryptocurrencyWalletRequest, digitalCurrencyAcronym);

        walletRepository
                .findByDigitalCurrencyAcronym(digitalCurrencyAcronym)
                .ifPresent(wallet::overrideWallet);

        return walletRepository.save(wallet);
    }
}
