package com.crypto.wallet.infra.database.mongodb.mongorepositories;

import com.crypto.wallet.infra.database.mongodb.documents.SalesHistoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ISalesHistoryMongoRepository extends MongoRepository<SalesHistoryDocument, String> {
}
