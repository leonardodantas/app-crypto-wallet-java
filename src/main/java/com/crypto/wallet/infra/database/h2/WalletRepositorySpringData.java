package com.crypto.wallet.infra.database.h2;

import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.domain.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WalletRepositorySpringData extends MongoRepository<Wallet, String> {
    Optional<Wallet> findByDigitalCurrencyAcronym(DigitalCurrencyAcronymDocument digitalCurrencyAcronym);
    Optional<Wallet> findByDigitalCurrencyAcronymName(String coin);
}
