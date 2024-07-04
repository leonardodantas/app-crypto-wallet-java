package com.crypto.wallet.infra.database.converters;

import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.infra.database.entities.SalesHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalesHistoryConverter implements Converter<SalesHistoryEntity, SalesHistory> {

    private final DigitalCurrencyAcronymConverter digitalCurrencyAcronymConverter;
    private final CryptoConverter cryptoConverterConverter;

    @Override
    public SalesHistory convert(final SalesHistoryEntity entity) {
        return SalesHistory.builder()
                .digitalCurrencyAcronym(digitalCurrencyAcronymConverter.convert(entity.getDigitalCurrencyAcronym()))
                .crypto(cryptoConverterConverter.convert(entity.getCrypto()))
                .date(entity.getDate())
                .quantity(entity.getQuantity())
                .build();
    }
}
