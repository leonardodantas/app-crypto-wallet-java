package com.crypto.wallet.infra.database.converters;

import com.crypto.wallet.domain.Crypto;
import com.crypto.wallet.infra.database.entities.CryptoEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CryptoEntityToCrypto implements Converter<CryptoEntity, Crypto> {

    @Override
    public Crypto convert(final CryptoEntity entity) {
        return Crypto.from(entity.getId(), entity.getTypeOperation().getType());
    }
}
