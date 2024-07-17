package com.crypto.wallet.infra.database.converters;

import com.crypto.wallet.domain.Crypto;
import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.SalesHistory;
import com.crypto.wallet.infra.database.entities.SalesHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalesHistoryConverter implements Converter<SalesHistoryEntity, SalesHistory> {


    @Override
    public SalesHistory convert(final SalesHistoryEntity entity) {
        return SalesHistory.builder()
                .digitalCurrencyAcronym(DigitalCurrencyAcronym.of(entity.getDigitalCurrencyAcronymName(), entity.getDigitalCurrencyAcronymDescription()))
                .crypto(Crypto.from(entity.getCryptoId(), entity.getCryptoTypeOperation()))
                .date(entity.getDate())
                .quantity(entity.getQuantity())
                .build();
    }
}
