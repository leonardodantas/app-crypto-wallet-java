package com.crypto.wallet.app.repositories;

import com.crypto.wallet.domain.SalesHistory;

public interface ISalesHistoryRepository {
    SalesHistory save(final SalesHistory salesHistory);
}
