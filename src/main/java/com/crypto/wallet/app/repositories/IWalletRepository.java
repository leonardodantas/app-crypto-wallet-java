package com.crypto.wallet.app.repositories;

import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;

import java.util.List;
import java.util.Optional;

public interface IWalletRepository {
    WalletDocument save(WalletDocument wallet);
    Optional<WalletDocument> findByDigitalCurrencyAcronym(DigitalCurrencyAcronymDocument digitalCurrencyAcronym);
    List<WalletDocument> findAll();
    Optional<WalletDocument> findByCryptocurrencyName(String cryptocurrency);
}
