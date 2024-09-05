package com.crypto.wallet.infra.database.mongodb.jpa;

import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DigitalCurrencyAcronymSpringData extends MongoRepository<DigitalCurrencyAcronymDocument, String> {
    Optional<DigitalCurrencyAcronymDocument> findByName(final String name);
}
