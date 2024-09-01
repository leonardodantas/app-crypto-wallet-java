package com.crypto.wallet.infra.database.h2;

import com.crypto.wallet.domain.DigitalCurrencyAcronymDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DigitalCurrencyAcronymSpringData extends MongoRepository<DigitalCurrencyAcronymDocument, String> {
    Optional<DigitalCurrencyAcronymDocument> findByName(String name);
}
