package com.crypto.wallet.infra.database.mongodb.mongorepositories;

import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IDigitalCurrencyAcronymMongoRepository extends MongoRepository<DigitalCurrencyAcronymDocument, String> {
    Optional<DigitalCurrencyAcronymDocument> findByName(final String name);
}
