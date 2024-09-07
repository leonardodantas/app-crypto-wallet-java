package com.crypto.wallet.infra.database.mongodb.repositories;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.infra.database.mongodb.documents.SalesHistoryDocument;
import com.crypto.wallet.infra.database.mongodb.jpa.SalesHistorySpringData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SalesHistoryRepository implements ISalesHistoryRepository {

    private final SalesHistorySpringData salesHistorySpringData;

    @Override
    public void save(final SalesHistory salesHistory) {
        try {
            salesHistorySpringData.save(SalesHistoryDocument.from(salesHistory));
        } catch (final Exception e) {
            throw new EntitySaveException(e.getMessage());
        }
    }
}
