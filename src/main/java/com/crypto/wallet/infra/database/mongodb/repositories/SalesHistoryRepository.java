package com.crypto.wallet.infra.database.mongodb.repositories;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.infra.database.mongodb.documents.SalesHistoryDocument;
import com.crypto.wallet.infra.database.mongodb.mongorepositories.ISalesHistoryMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SalesHistoryRepository implements ISalesHistoryRepository {

    private final ISalesHistoryMongoRepository salesHistoryMongoRepository;

    @Override
    public void save(final SalesHistory salesHistory) {
        try {
            salesHistoryMongoRepository.save(SalesHistoryDocument.from(salesHistory));
        } catch (final Exception e) {
            throw new EntitySaveException(e.getMessage());
        }
    }
}
