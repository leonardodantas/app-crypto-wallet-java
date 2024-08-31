package com.crypto.wallet.infra.database.mongodb.jpa;

import com.crypto.wallet.infra.database.mongodb.documents.DigitalCurrencyAcronymDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDigitalCurrencyAcronymMongoRepository extends MongoRepository<DigitalCurrencyAcronymDocument, String> {
}
