package com.crypto.wallet.infra.database.mongodb.mongorepositories;

import com.crypto.wallet.infra.database.mongodb.documents.TickerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITickerDocumentMongoRepository extends MongoRepository<TickerDocument, String> {
}
