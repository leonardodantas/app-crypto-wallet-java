package com.crypto.wallet.infra.database.impl;

import com.crypto.wallet.app.exceptions.EntitySaveException;
import com.crypto.wallet.app.repositories.ISalesHistoryRepository;
import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.infra.database.SalesHistorySpringData;
import com.crypto.wallet.infra.database.converters.SalesHistoryEntityToSalesHistory;
import com.crypto.wallet.infra.database.converters.SalesHistoryToSalesHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SalesHistoryRepository implements ISalesHistoryRepository {

    private final SalesHistorySpringData salesHistorySpringData;
    private final SalesHistoryToSalesHistoryEntity salesHistoryToSalesHistoryEntityConverter;
    private final SalesHistoryEntityToSalesHistory salesHistoryEntityToSalesHistoryConverter;

    @Override
    public SalesHistory save(final SalesHistory salesHistory) {
        try {
            final var salesHistoryToSave = salesHistoryToSalesHistoryEntityConverter.convert(salesHistory);
            final var salesHistorySave = salesHistorySpringData.save(salesHistoryToSave);
            return salesHistoryEntityToSalesHistoryConverter.convert(salesHistorySave);
        } catch (final Exception e) {
            throw new EntitySaveException("Error save SalesHistory");
        }
    }
}
