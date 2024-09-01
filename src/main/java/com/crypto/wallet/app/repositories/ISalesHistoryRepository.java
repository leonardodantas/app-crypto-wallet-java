package com.crypto.wallet.app.repositories;

import com.crypto.wallet.domain.SalesHistoryDocument;

public interface ISalesHistoryRepository {
    SalesHistoryDocument save(SalesHistoryDocument salesHistory);
}
