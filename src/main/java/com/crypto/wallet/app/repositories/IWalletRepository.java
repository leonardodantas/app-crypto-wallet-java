package com.crypto.wallet.app.repositories;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.infra.http.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.domain.Wallet;

import java.util.List;
import java.util.Optional;

public interface IWalletRepository {
    Wallet save(Wallet wallet);
    Optional<Wallet> findByDigitalCurrencyAcronym(final DigitalCurrencyAcronym digitalCurrencyAcronym);
    List<Wallet> findAll();
    Optional<Wallet> findByCryptocurrencyName(final String cryptocurrency);
}
