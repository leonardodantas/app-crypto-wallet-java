package com.crypto.wallet.infra.database.converters;

import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.infra.database.entities.SalesHistoryEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SalesHistoryToSalesHistoryEntity implements Converter<SalesHistory, SalesHistoryEntity> {
    @Override
    public SalesHistoryEntity convert(final SalesHistory domain) {
        return SalesHistoryEntity.from(domain);
    }
}
