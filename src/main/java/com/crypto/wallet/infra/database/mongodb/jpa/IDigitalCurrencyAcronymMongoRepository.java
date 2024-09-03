package com.crypto.wallet.infra.database.mongodb.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDigitalCurrencyAcronymMongoRepository extends MongoRepository<DigitalCurrencyAcronymDocument, String> {
}
