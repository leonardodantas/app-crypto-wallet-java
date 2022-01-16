package com.crypto.wallet.app.repositories;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;

import java.util.List;
import java.util.Optional;

public interface IWalletRepository {
    Wallet save(Wallet wallet);
    Optional<Wallet> findByDigitalCurrencyAcronym(DigitalCurrencyAcronym digitalCurrencyAcronym);
    List<Wallet> findAll();
    Optional<Wallet> findByCryptocurrencyName(String cryptocurrency);
}
