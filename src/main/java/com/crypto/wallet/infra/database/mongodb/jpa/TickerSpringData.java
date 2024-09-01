package com.crypto.wallet.infra.database.mongodb.jpa;

import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TickerSpringData extends MongoRepository<TickerDocument, String> {
}
