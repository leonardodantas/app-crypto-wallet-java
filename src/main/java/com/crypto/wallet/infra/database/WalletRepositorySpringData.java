package com.crypto.wallet.infra.database;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepositorySpringData extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByDigitalCurrencyAcronym(DigitalCurrencyAcronym digitalCurrencyAcronym);
    Optional<Wallet> findByDigitalCurrencyAcronymName(String coin);
}
