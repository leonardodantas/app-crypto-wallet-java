package com.crypto.wallet.infra.database.converters;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.infra.http.responses.DigitalCurrencyAcronymResponse;
import com.crypto.wallet.infra.database.entities.DigitalCurrencyAcronymEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DigitalCurrencyAcronymConverter implements Converter<DigitalCurrencyAcronymEntity, DigitalCurrencyAcronym> {

    @Override
    public DigitalCurrencyAcronym convert(final DigitalCurrencyAcronymEntity entity) {
        return new DigitalCurrencyAcronym(entity.getName(), entity.getDescription());
    }
}
