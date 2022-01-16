package com.crypto.wallet.app.usecases.impl;

import com.crypto.wallet.app.models.requests.CryptocurrencyWalletRequest;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.app.usecases.ISaveWallet;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.domain.TypeOperation;
import com.crypto.wallet.domain.Wallet;
import org.springframework.stereotype.Service;

@Service
public class SaveWallet implements ISaveWallet {

    private final ISalesHistoryRepository salesHistoryRepository;
    private final IWalletRepository walletRepository;

    public SaveWallet(ISalesHistoryRepository salesHistoryRepository, IWalletRepository walletRepository) {
        this.salesHistoryRepository = salesHistoryRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet save(CryptocurrencyWalletRequest cryptocurrencyWalletRequest, DigitalCurrencyAcronym digitalCurrencyAcronym) {
        SalesHistory salesHistory = SalesHistory.of(cryptocurrencyWalletRequest, digitalCurrencyAcronym, TypeOperation.BUY);
        salesHistoryRepository.save(salesHistory);

        Wallet wallet = Wallet.of(cryptocurrencyWalletRequest, digitalCurrencyAcronym);

        walletRepository
                .findByDigitalCurrencyAcronym(digitalCurrencyAcronym)
                .ifPresent(wallet::overrideWallet);

        return walletRepository.save(wallet);
    }
}
