package com.crypto.wallet.infra.database.h2;

import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import com.crypto.wallet.infra.database.mongodb.documents.WalletDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WalletRepositorySpringData extends MongoRepository<WalletDocument, String> {
    Optional<WalletDocument> findByDigitalCurrencyAcronym(DigitalCurrencyAcronymDocument digitalCurrencyAcronym);
    Optional<WalletDocument> findByDigitalCurrencyAcronymName(String coin);
}
