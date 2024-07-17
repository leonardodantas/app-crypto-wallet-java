package com.crypto.wallet.infra.database.converters;

import com.crypto.wallet.domain.DigitalCurrencyAcronym;
import com.crypto.wallet.domain.Wallet;
import com.crypto.wallet.infra.database.entities.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletConverter implements Converter<WalletEntity, Wallet> {

    @Override
    public Wallet convert(final WalletEntity entity) {
        return Wallet.builder()
                .id(entity.getId())
                .digitalCurrencyAcronym(DigitalCurrencyAcronym.of(entity.getDigitalCurrencyAcronymName(), entity.getDigitalCurrencyAcronymDescription()))
                .quantity(entity.getQuantity())
                .build();
    }
}
