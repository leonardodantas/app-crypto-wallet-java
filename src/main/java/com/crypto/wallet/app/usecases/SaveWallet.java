package com.crypto.wallet.app.usecases;

import com.crypto.wallet.infra.controllers.jsons.requests.Cryptocurrency;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.app.repositories.IWalletRepository;
import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.documents.SalesHistoryDocument;
import com.crypto.wallet.domain.TypeOperation;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveWallet {

    private final ISalesHistoryRepository salesHistoryRepository;
    private final IWalletRepository walletRepository;

    public WalletDocument save(final Cryptocurrency cryptocurrency, final DigitalCurrencyAcronymDocument digitalCurrencyAcronym) {
        final SalesHistoryDocument salesHistory = SalesHistoryDocument.of(cryptocurrency, digitalCurrencyAcronym, TypeOperation.BUY);
        salesHistoryRepository.save(salesHistory);

        final WalletDocument wallet = WalletDocument.of(cryptocurrency, digitalCurrencyAcronym);

        walletRepository
                .findByDigitalCurrencyAcronym(digitalCurrencyAcronym)
                .ifPresent(wallet::overrideWallet);

        return walletRepository.save(wallet);
    }
}
