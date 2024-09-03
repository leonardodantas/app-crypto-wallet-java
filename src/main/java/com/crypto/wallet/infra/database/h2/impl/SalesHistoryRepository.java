package com.crypto.wallet.infra.database.h2.impl;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.infra.database.mongodb.documents.SalesHistoryDocument;
import com.crypto.wallet.infra.database.h2.SalesHistorySpringData;
import org.springframework.stereotype.Repository;

@Repository
public class SalesHistoryRepository implements ISalesHistoryRepository {

    private final SalesHistorySpringData salesHistorySpringData;

    public SalesHistoryRepository(SalesHistorySpringData salesHistorySpringData) {
        this.salesHistorySpringData = salesHistorySpringData;
    }

    @Override
    public SalesHistoryDocument save(SalesHistoryDocument salesHistory) {
        try {
            return salesHistorySpringData.save(salesHistory);
        } catch (Exception e){
            throw new EntitySaveException(e.getMessage());
        }
    }
}
