package com.crypto.wallet.infra.database.h2;

import com.crypto.wallet.infra.database.mongodb.documents.SalesHistoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesHistorySpringData extends MongoRepository<SalesHistoryDocument, String> {
}
