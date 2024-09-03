package com.crypto.wallet.app.repositories;

import com.crypto.wallet.infra.database.mongodb.documents.SalesHistoryDocument;

public interface ISalesHistoryRepository {
    SalesHistoryDocument save(SalesHistoryDocument salesHistory);
}
