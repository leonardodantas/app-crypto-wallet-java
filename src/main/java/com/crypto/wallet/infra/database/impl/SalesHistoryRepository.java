package com.crypto.wallet.infra.database.impl;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.infra.database.SalesHistorySpringData;
import org.springframework.stereotype.Repository;

@Repository
public class SalesHistoryRepository implements ISalesHistoryRepository {

    private final SalesHistorySpringData salesHistorySpringData;

    public SalesHistoryRepository(SalesHistorySpringData salesHistorySpringData) {
        this.salesHistorySpringData = salesHistorySpringData;
    }

    @Override
    public SalesHistory save(SalesHistory salesHistory) {
        try {
            return salesHistorySpringData.save(salesHistory);
        } catch (Exception e){
            throw new EntitySaveException(e.getMessage());
        }
    }
}
