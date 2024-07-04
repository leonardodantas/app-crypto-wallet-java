package com.crypto.wallet.infra.database.converters;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.infra.database.entities.DigitalCurrencyAcronymEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DigitalCurrencyAcronymEntityToDigitalCurrencyAcronym implements Converter<DigitalCurrencyAcronymEntity, DigitalCurrencyAcronym> {

    @Override
    public DigitalCurrencyAcronym convert(final DigitalCurrencyAcronymEntity entity) {
        return DigitalCurrencyAcronym.builder()
                .description(entity.getDescription())
                .name(entity.getName())
                .build();
    }
}
